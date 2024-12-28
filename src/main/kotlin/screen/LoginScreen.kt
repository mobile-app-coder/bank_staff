package screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import utils.AppColors.sidebarColor
import utils.AppColors.textColor
import utils.AppColors.textFieldColor
import utils.AppModifiers
import viewmodels.LoginScreenViewModel

class LoginScreen : Screen {
    @OptIn(ExperimentalMaterialApi::class)
    @Preview
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = LoginScreenViewModel()
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = textFieldColor,
            focusedBorderColor = sidebarColor,
            unfocusedBorderColor = Color.Gray,
            textColor = textColor,
            cursorColor = sidebarColor
        )

        val branches = listOf("Main Branch", "City Center", "Suburban Branch")
        var expanded by remember { mutableStateOf(false) }
        var selectedBranch by remember { mutableStateOf(branches[0]) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Login") }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(Modifier.fillMaxWidth(0.6f)) {
                    OutlinedTextField(
                        onValueChange = { text ->
                            viewModel.user.value = text
                        },
                        value = viewModel.user.value,
                        label = { Text("Username") },
                        modifier = AppModifiers.textFieldModifier,
                        colors = textFieldColors,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        onValueChange = { text: String ->
                            viewModel.password.value = text
                        },
                        value = viewModel.password.value,
                        label = { Text("Password") },
                        modifier = AppModifiers.textFieldModifier,
                        colors = textFieldColors
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Branch selection DropdownMenu
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedBranch,
                            onValueChange = {},
                            label = { Text("Select Branch") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            colors = textFieldColors,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            branches.forEach { branch ->
                                DropdownMenuItem(onClick = {
                                    selectedBranch = branch
                                    expanded = false
                                }) {
                                    Text(branch)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                    onClick = {
                        // Store selected branch in ViewModel or perform navigation with branch
                        viewModel.branch = selectedBranch
                        viewModel.login(navigator)
                    },
                    content = {
                        Text("Log In")
                    }
                )
            }
        }
    }
}
