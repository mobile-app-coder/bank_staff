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
import cafe.adriel.voyager.core.screen.Screen
import model.TransactionModel


// Transaction History Screen
class TransactionHistoryScreen : Screen {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Transaction History", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            TransactionList()
        }
    }

    @Composable
    fun TransactionList() {
        val transactions = listOf(
            TransactionModel(
                transactionId = "",
                date = "2024-12-22",
                type = "Withdrawal",
                amount = "$300",
                status = "Completed"
            )
        )
        LazyColumn {
            items(transactions.size) { index ->
                val transaction = transactions[index]
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
                        "Status: ${transaction.status}",
                        fontSize = 16.sp,
                        color = when (transaction.status) {
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
