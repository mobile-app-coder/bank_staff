package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.delay
import model.ApplicationModel
import viewmodels.ApplicationViewModel


// Applications Screen
class ApplicationsScreen : Screen {


    @Composable
    override fun Content() {

        val applicationViewModel = viewModel { ApplicationViewModel() }
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Applications Overview", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            ApplicationsList(applicationViewModel)
        }
    }

    @Composable
    fun ApplicationsList(applicationViewModel: ApplicationViewModel) {

        LazyColumn {
            items(applicationViewModel.applicationList.size) { index ->
                val application = applicationViewModel.applicationList[index]
                ApplicationItemView(
                    application,
                    onProve = { applicationId ->
                        applicationViewModel.approve(applicationId)
                    },
                    onReject = { applicationId ->
                        applicationViewModel.reject(applicationId)
                    })
            }
        }
    }

    @Composable
    fun ApplicationItemView(application: ApplicationModel, onProve: (String) -> Unit, onReject: (String) -> Unit) {
        var isExpanded by remember { mutableStateOf(false) }

        LaunchedEffect(isExpanded) {
            if (isExpanded) {
                delay(20000) // Wait for 20 seconds
                isExpanded = false // Collapse after 20 seconds
            }
        }
        // Card for the application item
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Row with basic information
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "${application.applicationType} Application",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text("Client: ${application.clientName}", fontSize = 16.sp)
                        Text("Amount: ${application.amount}", fontSize = 16.sp)
                        Text(
                            "Status: ${application.status}", fontSize = 16.sp, color = when (application.status) {
                                "Approved" -> Color.Green
                                "Pending" -> Color.Yellow
                                "Rejected" -> Color.Red
                                else -> Color.Gray
                            }
                        )
                    }

                    Spacer(Modifier.width(16.dp))

                    // Button to toggle expansion
                    OutlinedButton(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(if (isExpanded) "Hide Details" else "Show Details")
                    }
                }

                // Expandable content
                if (isExpanded) {
                    Spacer(modifier = Modifier.height(16.dp))
                    // Show additional details when expanded
                    ApplicationDetailView(application)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons (Approve/Reject)
                    Row(Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { onProve(application.applicationID) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Approve")
                        }


                        Spacer(Modifier.width(20.dp))
                        OutlinedButton(
                            onClick = { onReject(application.applicationID) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Reject")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

    // A composable to show the application details when expanded
    @Composable
    fun ApplicationDetailView(application: ApplicationModel) {
        Column(modifier = Modifier) {
            Text("Details", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Application ID: ${application.applicationID}", fontSize = 16.sp)
            Text("Amount: ${application.amount}", fontSize = 16.sp)
            Text("Application Type: ${application.applicationType}", fontSize = 16.sp)
            Text("Client Name: ${application.clientName}", fontSize = 16.sp)
            Text("Status: ${application.status}", fontSize = 16.sp)
            // Add more fields as necessary
        }
    }
}
