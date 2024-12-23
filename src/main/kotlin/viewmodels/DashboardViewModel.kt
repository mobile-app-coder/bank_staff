package viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.InfoModel
import model.TransactionModel

class DashboardViewModel : ViewModel() {
    private val _recentActivities = mutableStateListOf<TransactionModel>()
    val recentActivities: SnapshotStateList<TransactionModel> = _recentActivities

    private val _infoList = mutableStateListOf<InfoModel>()
    val infoList: SnapshotStateList<InfoModel> = _infoList


    private suspend fun getRecentActivities() {
        for (i in 1..10) {
            //_recentActivities.a
            _recentActivities.add(
                TransactionModel(
                    transactionId = "",
                    date = "2024-12-22",
                    type = "Deposit",
                    amount = "$500",
                    status = "Completed"
                ),
            )
            delay(2000)
        }
    }

    private suspend fun getGeneralInfo() {
        delay(4000)
        for (i in 1..4) {
            _infoList.add(InfoModel(type = "Loan", amount = "10", totalMoney = "1000000"))

        }
    }

    init {
        println("DashboardViewmodel init called")
        CoroutineScope(Dispatchers.IO).launch {
            getGeneralInfo()

            getRecentActivities()
        }
    }
}