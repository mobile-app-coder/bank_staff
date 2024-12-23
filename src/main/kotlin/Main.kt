import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.Network
import screen.HomeScreen
import screen.LoginScreen


fun main() = application {
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch(Dispatchers.IO) {
        Network.sendMessage("staff")
    }
    Window(onCloseRequest = ::exitApplication) {
        Navigator(HomeScreen())
    }
}
