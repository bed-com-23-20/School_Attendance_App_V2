package com.example.school_attendance_register


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginTemp() {
    val authViewModel: AuthViewModel = viewModel()
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        // Show logged-in content
        Text("Logged in successfully")
    } else {
        // Show login button
        Button(
            onClick = {
                loading = true
                authViewModel.fetchCredentials({ email, password ->
                    authViewModel.login(email, password, {
                        loading = false
                        isLoggedIn = true
                    }, { loginError ->
                        loading = false
                        error = loginError
                    })
                }, { fetchError ->
                    loading = false
                    error = fetchError
                })
            }
        ) {
            Text(if (loading) "Logging in..." else "Login")
        }

        error?.let {
            Text("Error: $it", color = Color.Red)
        }
    }
}
