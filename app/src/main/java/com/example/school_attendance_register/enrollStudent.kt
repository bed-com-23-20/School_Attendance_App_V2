package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EnrollStudent(
    onEnroll: (String, String, String, String, String) -> Unit, // Callback for enrollment
    onBack: () -> Unit // Callback for navigating back
) {
    // State variables for inputs
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) } // Guardian Name state
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) } // Guardian Phone state
    var className by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf(TextFieldValue("")) } // Date of Birth state

    //  a Column layout for input fields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start // Align elements to the start
    ) {
        Text(text = "Enroll Student", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // student name Input
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Guardian Name Input
        TextField(
            value = guardianName,
            onValueChange = { guardianName = it },
            label = { Text("Guardian Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Guardian Phone Number Input
        TextField(
            value = guardianPhone,
            onValueChange = { guardianPhone = it },
            label = { Text("Guardian Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Class Input
        TextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date of Birth Input
        TextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Enroll Button
        Button(
            onClick = {
                onEnroll(name.text, guardianName.text, guardianPhone.text, className.text, dateOfBirth.text) // Call enrollment with input values
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Enroll")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Back Button
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back")
        }
    }
}

// Preview function for the EnrollStudent composable
@Preview(showBackground = true)
@Composable
fun PreviewEnrollStudent() {
    EnrollStudent(
        onEnroll = { name, guardianName, guardianPhone, className, dateOfBirth -> },
        onBack = {}
    )
}
