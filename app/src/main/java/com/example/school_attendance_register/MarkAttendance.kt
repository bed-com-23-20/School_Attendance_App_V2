package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MarkAttendance() {
    // State to hold the student code input
    var studentCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(text = "Mark Attendance", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Student Code Label
        Text(text = "Student Code", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // TextField for input
        TextField(
            value = studentCode,
            onValueChange = { studentCode = it },
            placeholder = { Text("Enter student code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Handle Cancel */ }) {
                Text(text = "Cancel")
            }

            Button(onClick = { /* Handle Submit */ }) {
                Text(text = "Submit")
            }
        }
    }
}
