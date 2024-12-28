package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.LoginModel
import model.convertLoginModelToJson
import network.Network
import network.Network.getMessage
import screen.HomeScreen


class LoginScreenViewModel : ViewModel() {
    var user = mutableStateOf("")
    var password = mutableStateOf("")
    var loginStatus = mutableStateOf("")
    var branch by mutableStateOf("")

    fun login(navigator: Navigator) {
        CoroutineScope(Dispatchers.IO).launch {
            try {


                val model = LoginModel(user.value, password.value)
                val params = convertLoginModelToJson(model)
                println("Serialized login request: $params")
                val request = "login:$params"
                Network.sendMessage(request)

                var response = ""
                while (response.isEmpty()) {
                    println("Waiting for response...")
                    response = (getMessage() ?: "null")
                    println(response)
                }

                if (response == "login") {
                    navigator.replace(HomeScreen())
                    loginStatus.value = "Login successful"
                    println("Login successful")

                } else {
                    loginStatus.value = "Login failed"
                    println("Login failed: $response")
                }
            } catch (e: Exception) {
                loginStatus.value = "Error: ${e.message}"
                println("Login error: ${e.message}")
            }
        }
    }
}
