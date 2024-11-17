package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.ui.components.PageWithBackArrow

@Composable
fun MarkAttendance(navController: NavController) {
    // State to hold the student code input
    var studentCode by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Mark Attendance") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Mark Attendance",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Student Code Input Field
            OutlinedTextField(
                value = studentCode,
                onValueChange = { studentCode = it },
                label = { Text(text = "Enter student code") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Display feedback message if attendance is marked
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = Color.Green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate("Admin_Dash_Board") },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Cancel", color = Color.White)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        // Logic to mark attendance
                        if (studentCode.isNotBlank()) {
                            message = "Attendance marked for student code: $studentCode"
                            studentCode = "" // Reset input after submission
                        } else {
                            message = "Please enter a valid student code."
                        }
                    },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Submit", color = Color.White)
                }
            }
        }
    }
}
