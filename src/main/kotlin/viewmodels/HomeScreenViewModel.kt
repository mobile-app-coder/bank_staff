package viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import model.ActivityModel
import java.math.BigDecimal
import java.time.LocalDate

class HomeScreenViewModel : ViewModel() {

    val recentActivities = mutableStateListOf<ActivityModel>()


    fun getRecentActivities() {
        for (i in 0..10) {
            recentActivities.add(
                ActivityModel(
                    activityID = "ACT123",
                    clientName = "John Doe",
                    applicationType = "Loan",
                    amount = BigDecimal("1000.50").toString(),
                    date = LocalDate.of(2024, 10, 27).toString(),
                    status = "Pending"
                )
            )
        }
    }

    init {
        getRecentActivities()
    }
}