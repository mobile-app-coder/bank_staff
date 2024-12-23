package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            "Account Closure - Jane Doe - High",
            "Loan Inquiry - John Smith - Medium"
        )
        LazyColumn {
            items(requests.size) { index ->
                val request = requests[index]
                RequestItemView(request)
            }
        }
    }

    @Composable
    fun RequestItemView(request: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(request, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
