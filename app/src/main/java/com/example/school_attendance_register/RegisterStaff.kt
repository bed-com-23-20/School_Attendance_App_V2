package com.example.school_attendance_register
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun RegisterStaff(
    onRegister: (String, String, String, String, String) -> Unit, // Callback for registration
    onBack: () -> Unit // Callback for navigating back
) {
    // State variables for inputs
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) } // Staff phone number
    var email by remember { mutableStateOf(TextFieldValue("")) } // Staff email
    var className by remember { mutableStateOf(TextFieldValue("")) } // Class the staff teaches

    var showError by remember { mutableStateOf(false) } // State for showing error message

    // Main Column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header for "Register Staff"
        Text(
            text = "STAFF REGISTRATION PAGE", // Adjusted heading text
            fontSize = 28.sp,
            fontFamily =
            FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )
        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // A Column layout for input fields
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start // Align elements to the start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Staff first name input
            OutlinedTextField(
                value = fname,
                onValueChange = { fname = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Staff surname input
            OutlinedTextField(
                value = sname,
                onValueChange = { sname = it },
                label = { Text("Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Staff phone number input
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Staff email input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Class input
            OutlinedTextField(
                value = className,
                onValueChange = { className = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Show error message if form is incomplete
            if (showError) {
                Text(
                    text = "Please fill all the necessary details before submitting.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    // Validation check
                    if (fname.text.isBlank() || sname.text.isBlank() || phone.text.isBlank() ||
                        email.text.isBlank() || className.text.isBlank()
                    ) {
                        // If any field is empty, show error message
                        showError = true
                    } else {
                        // All fields are filled, proceed with registration
                        showError = false
                        onRegister(
                            fname.text,
                            sname.text,
                            phone.text,
                            email.text,
                            className.text
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Back Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Back")
            }
        }
    }
}

// Preview function for the RegisterStaff composable
@Preview(showBackground = true)
@Composable
fun PreviewRegisterStaff() {
    RegisterStaff(
        onRegister = { fname, sname, phone, email, className -> },
        onBack = {}
    )
}
