package viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.ApplicationModel

class ApplicationViewModel : ViewModel() {

    private val _applicationList = mutableStateListOf<ApplicationModel>()
    val applicationList: SnapshotStateList<ApplicationModel> = _applicationList


    private suspend fun getApplications() {
        for (i in 1..10) {
            _applicationList.add(
                ApplicationModel(
                    applicationID = "$i",
                    clientName = "John Doe",
                    applicationType = "Loan",
                    amount = "$5,000",
                    status = "Pending"
                ),
            )
            delay(1000)
        }
    }


    fun approve(applicationId: String) {
        println("Approval called id$applicationId")
        //implementation of approve application
    }

    fun reject(applicationId: String) {
        println("rejection called id$applicationId")
        //implementation of reject application
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getApplications()
        }
    }
}