package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


// Customer Requests Screen
class CustomerRequestsScreen : Screen {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Customer Requests", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            RequestsList()
        }
    }

    @Composable
    fun RequestsList() {
        val requests = listOf(
            Request("Account Closure", "Jane Doe", "High"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
            Request("Loan Inquiry", "John Smith", "Medium"),
        )
        LazyColumn {
            items(requests.size) { index ->
                val request = requests[index]
                RequestItemView(request)
            }
        }
    }

    @Composable
    fun RequestItemView(request: Request) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                // Priority Icon
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    tint = when (request.priority) {
                        "High" -> Color.Red
                        "Medium" -> Color.Yellow
                        else -> Color.Gray
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                // Request Details
                Column {
                    Text(request.type, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Client: ${request.client}", fontSize = 16.sp)
                    Text("Priority: ${request.priority}", fontSize = 16.sp)
                }
            }
        }
    }
}

// Data class for a Request
data class Request(
    val type: String,
    val client: String,
    val priority: String
)
