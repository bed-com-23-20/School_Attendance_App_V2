package com.example.school_attendance_register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun AdminDashBoardPage() {
    // Stating variables for inputs
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var className by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) } // Password state

    // Column layout for input fields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start // Align elements to the start
    ) {
        Text(text = "Register Staff", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Name Input
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Name")
        })

        Spacer(modifier = Modifier.height(8.dp))

        // Email Input
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Email")
        })

        Spacer(modifier = Modifier.height(8.dp))

        // Phone Number Input
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Phone Number")
        })

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Class")
        })

        Spacer(modifier = Modifier.height(8.dp))

        // Password Input
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Password")
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = {
                onRegister(name.text, email.text, phone.text, className.text, password.text) // Call registration with input values
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Back Button

    }
}

fun onRegister(text: String, text1: String, text2: String, text3: String, text4: String) {
    TODO("Not yet implemented")
}

@Composable
fun RegistrationScreen(onRegister: (String, String, String, String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var className by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
        TextField(value = className, onValueChange = { className = it }, label = { Text("Class Name") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        Button(onClick = {
            onRegister(name, email, phone, className, password) // Call registration with input values
        }) {
            Text("Register")
        }
    }
}
