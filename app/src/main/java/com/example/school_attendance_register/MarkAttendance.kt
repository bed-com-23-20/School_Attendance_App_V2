package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Text(text = "Mark Attendance", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(40.dp))

        // Student Code Label
        Text(text = "Student Code", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Enter student code")
        })

        Spacer(modifier = Modifier.height(30.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center-aligns the buttons in the Row
        ) {
            Button(onClick = { /* Handle Cancel */ }) {
                Text(text = "Cancel")
            }

            Spacer(modifier = Modifier.width(40.dp)) // Space between the two buttons

            Button(onClick = { /* Handle Submit */ }) {
                Text(text = "Submit")
            }
        }


    }
}
