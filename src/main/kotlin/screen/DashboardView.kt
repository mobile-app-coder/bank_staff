package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import model.BankAccount
import model.InfoModel
import model.TransactionModel

// Dashboard Screen
class DashboardScreen : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Welcome to the Dashboard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))

            // Overview Section
            DashboardOverview()
            Spacer(modifier = Modifier.height(10.dp))

            // Recent Activity
            RecentActivity()
            Spacer(modifier = Modifier.height(10.dp))

            // New Accounts for Approval
            NewAccountsForApproval()
        }
    }

    @Composable
    fun DashboardOverview() {
        val infoList = listOf(
            InfoModel(
                type = "Checking Account", totalMoney = 1000000.0.toString(),
                amount = 2000.toString()
            ),
            InfoModel(
                type = "Savings Account", totalMoney = 500000.0.toString(),
                amount = 2932.toString()
            ),
            InfoModel(
                type = "Loans", totalMoney = 1500000.0.toString(),
                amount = 2389.toString()
            )
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            infoList.forEach {
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
    fun RecentActivity() {
        val recentActivities = listOf(
            TransactionModel(date = "2024-12-25", type = "Deposit", amount = 5000.0.toString(), status = "Completed"),
            TransactionModel(date = "2024-12-20", type = "Withdrawal", amount = 1000.0.toString(), status = "Pending"),
            TransactionModel(date = "2024-12-15", type = "Transfer", amount = 3000.0.toString(), status = "Failed")
        )

        Text("Recent Activity", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyColumn {
            items(recentActivities.size) { index ->
                val transaction = recentActivities[index]
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

    @Composable
    fun NewAccountsForApproval() {
        val newAccounts = listOf(
            BankAccount(accountHolder = "Jane Smith", accountTypeId = "Savings", balance = 2000.0, status = "Pending"),
            BankAccount(accountHolder = "John Doe", accountTypeId = "Checking", balance = 5000.0, status = "Pending")
        )

        Text("New Accounts for Approval", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // List of new accounts awaiting approval
        LazyColumn {
            items(newAccounts.size) { index ->
                val account = newAccounts[index]
                AccountItemView(account)
            }
        }
    }

    @Composable
    fun AccountItemView(account: BankAccount) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Account Holder: ${account.accountHolder}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Account Type: ${account.accountTypeId}", fontSize = 16.sp)
                Text("Balance: ${account.balance}", fontSize = 16.sp)
                Text("Status: ${account.status}", fontSize = 16.sp, color = Color.Gray)

                // Buttons for approving or rejecting the account
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = {
                            // Approve the account
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text("Approve", color = Color.White)
                    }

                    Button(
                        onClick = {
                            // Reject the account
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text("Reject", color = Color.White)
                    }
                }
            }
        }
    }
}
