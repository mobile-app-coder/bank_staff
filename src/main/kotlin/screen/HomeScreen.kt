package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator


// Main Home Screen
class HomeScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Bank Staff Dashboard", color = Color.White) },
                    backgroundColor = Color(0xFF6200EE),
                    navigationIcon = {
                        IconButton(onClick = { /* Open Drawer */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = {
                Navigator(DashboardScreen()) { navigator: Navigator ->
                    Row(modifier = Modifier.fillMaxSize()) {
                        SidebarContent(navigator)
                        Box(Modifier.fillMaxSize().padding(16.dp)) {
                            navigator.lastItem.Content()
                        }
                    }
                }
            },
            backgroundColor = Color(0xFFF1F1F1)
        )
    }

    @Composable
    fun SidebarContent(navigator: Navigator) {
        val currentScreen = navigator.lastItem

        val selectedItem = remember(currentScreen) {
            when (currentScreen) {
                is DashboardScreen -> "Dashboard"
                is ApplicationsScreen -> "Applications"
                is TransactionHistoryScreen -> "Transaction History"
                is CustomerRequestsScreen -> "Customer Requests"
                else -> "Dashboard"
            }
        }
        Column(
            modifier = Modifier
                .width(250.dp)
                .fillMaxHeight()
                .background(Color(0xFF6200EE))
                .padding(16.dp)
        ) {
            SidebarItem("Dashboard", selectedItem == "Dashboard") { navigator.replace(DashboardScreen()) }
            SidebarItem("Applications", selectedItem == "Applications") { navigator.replace(ApplicationsScreen()) }
            SidebarItem("Customer Requests", selectedItem == "Customer Requests") {
                navigator.replace(CustomerRequestsScreen())
            }
            SidebarItem("Transaction History", selectedItem == "Transaction History") {
                navigator.replace(TransactionHistoryScreen())
            }
        }
    }

    @Composable
    fun SidebarItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
        Text(
            text = title,
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isSelected) Color(0xFF003399) else Color.Transparent)
                .clickable { onClick() }
                .padding(8.dp)
        )
    }
}

