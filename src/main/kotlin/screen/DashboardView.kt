package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import model.InfoModel
import model.TransactionModel
import viewmodels.DashboardViewModel


// Dashboard Screen
class DashboardScreen : Screen {


    @Composable
    override fun Content() {
        val dashboardViewModel = viewModel { DashboardViewModel() }
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Welcome to the Dashboard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            // Overview Section
            DashboardOverview(dashboardViewModel)
            Spacer(modifier = Modifier.height(20.dp))
            // Recent Activity
            RecentActivity(dashboardViewModel)
        }
    }

    @Composable
    fun DashboardOverview(dashboardViewModel: DashboardViewModel) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            dashboardViewModel.infoList.forEach {
                DashboardItem(info = it, Modifier.weight(1f).padding(16.dp))
            }
        }
    }

    @Composable
    fun DashboardItem(info: InfoModel, modifier: Modifier) {
        Card(modifier = modifier, elevation = 4.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(info.type, fontWeight = FontWeight.Bold)
                Text("$${info.totalMoney}", fontSize = 18.sp)
            }
        }
    }

    @Composable
    fun RecentActivity(dashboardViewModel: DashboardViewModel) {

        Text("Recent Activity", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyColumn {
            items(dashboardViewModel.recentActivities.size) { index ->
                val transaction = dashboardViewModel.recentActivities[index]
                TransactionItemView(transaction)
            }
        }
    }

    @Composable
    fun TransactionItemView(transaction: TransactionModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text("Date: ${transaction.date}", fontSize = 16.sp)
                    Text("Type: ${transaction.type}", fontSize = 16.sp)
                    Text("Amount: ${transaction.amount}", fontSize = 16.sp)
                    Text(
                        "Status: ${transaction.status}", fontSize = 16.sp, color = when (transaction.status) {
                            "Completed" -> Color.Green
                            "Pending" -> Color.Yellow
                            "Failed" -> Color.Red
                            else -> Color.Gray
                        }
                    )
                }
            }
        }
    }
}
