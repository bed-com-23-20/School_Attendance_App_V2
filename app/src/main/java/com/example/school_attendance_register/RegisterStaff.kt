package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.school_attendance_register.StaffViewModel // Ensure this import is correct

@Composable
fun RegisterStaff(viewModel: StaffViewModel = viewModel()) {
    // State variables for input fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var className by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register Staff", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = className, onValueChange = { className = it }, label = { Text("Class") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.registerStaff(name, email, phone, className, password) { success, msg ->
                    message = if (success) {
                        "Registration successful!"
                    } else {
                        msg
                    }
                }
            },
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display feedback message
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = if (message.contains("successful")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
