package viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import model.AccountGsonConverter
import model.BankAccount
import model.InfoModel
import model.TransactionModel
import network.Network

class DashboardViewModel : ViewModel() {
    private val _recentActivities = mutableStateListOf<TransactionModel>()
    val recentActivities: SnapshotStateList<TransactionModel> = _recentActivities

    private val _infoList = mutableStateListOf<InfoModel>()
    val infoList: SnapshotStateList<InfoModel> = _infoList

    private val _newAccounts = mutableStateListOf<BankAccount>()
    val newAccounts: SnapshotStateList<BankAccount> = _newAccounts

    // Simulate fetching recent activities
    private suspend fun getRecentActivities() {
        for (i in 1..10) {
            _recentActivities.add(
                TransactionModel(
                    transactionId = "", date = "2024-12-22", type = "Deposit", amount = "$500", status = "Completed"
                )
            )
            delay(2000) // Simulate delay
        }
    }

    // Simulate fetching general info for the dashboard
    private suspend fun getGeneralInfo() {
        delay(4000) // Simulate delay
        for (i in 1..4) {
            _infoList.add(InfoModel(type = "Loan", amount = "10", totalMoney = "1000000"))
        }
    }

    // Simulate fetching new accounts for approval
    private suspend fun getNewAccountsForApproval() {
        // Simulate delay
        try {
            val message = Network.getMessage()

            if (message != null && message.startsWith("newaccount")) {
                println(message)
                val accountString = message.split("|")[1]
                println(accountString)
                val accounts = AccountGsonConverter.fromJsonList(accountString)
                println(accounts)
                if (accounts != null) {
                    println("accounts:${accounts.toString()}")
                    withContext(Dispatchers.Main) {
                        _newAccounts.addAll(accounts)
                    }
                }
            }
        } catch (e: Exception) {
            println("Error at getting accounts: $e")
        }
    }

    // Approve an account
    fun approveAccount(account: BankAccount) {
        account.status = "Active"
        _newAccounts.remove(account) // Remove from pending list
        // You can add the account to the list of approved accounts if needed
    }

    // Reject an account
    fun rejectAccount(account: BankAccount) {
        _newAccounts.remove(account) // Remove from pending list
        // You can add logic to handle rejected accounts if needed
    }

    // Initialize and load data
    init {
        println("DashboardViewModel init called")

        // Use viewModelScope to ensure proper lifecycle handling
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) { // Check if the ViewModel is still active
                delay(100) // Simulate delay for checking messages
                getNewAccountsForApproval()
            }
        }
    }
}
