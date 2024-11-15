package com.example.school_attendance_register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegisterStaff(
    navController: NavController,
    viewModel: StaffViewModel? = null // Allow null for preview
) {
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
        Text(
            text = "Register Staff",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Provide a sample behavior in preview
                message = if (name.isNotEmpty() && email.isNotEmpty()) {
                    "Registration successful!"
                } else {
                    "Please fill out all fields"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display feedback message
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = if (message.contains("successful")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterStaff() {
    val navController = rememberNavController() // Mock NavController for preview
    RegisterStaff(navController = navController, viewModel = null) // Pass null to bypass ViewModel
}
