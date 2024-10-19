package com.example.school_attendance_register

// AdminDashboardScreen.kt

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AdminDashBoard(
    onCreateStaffAccount: () -> Unit,
    onCreateEnrollStudent: () -> Unit,
    onViewAttendance: () -> Unit
) {
    // Create a column to hold the entire admin dashboard
    Column(
        modifier = Modifier
            .fillMaxSize()  // Take up full available space
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,  // Space between heading and buttons
        horizontalAlignment = Alignment.CenterHorizontally  // Center horizontally
    ) {
        // Admin Dashboard heading at the top
        Text(
            text = "Admin Dashboard",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)  // Add some space below the heading
        )

        // Spacer to push the buttons to the center of the screen
        Spacer(modifier = Modifier.weight(1f))

        // Buttons centered vertically
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center, // Center items vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center items horizontally
        ) {
            // Button to create a teacher account
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,  // Set the button's container color to black
                    contentColor = Color.White     // Set the text color to white for contrast
                ),
                onClick = onCreateStaffAccount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "Register Staff")
            }

            // Button to create student account
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,  // Set the button's container color to black
                    contentColor = Color.White     // Set the text color to white for contrast
                ),
                onClick = onCreateEnrollStudent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "Enroll Student")
            }

            // Button to view records
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,  // Set the button's container color to black
                    contentColor = Color.White     // Set the text color to white for contrast
                ),
                onClick = onViewAttendance,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "View Attendance")
            }
        }

        // Spacer to push content to the center
        Spacer(modifier = Modifier.weight(1f))
    }
}

//shows on screen as a preview
@Preview(showBackground = true)
@Composable
fun PreviewAdminDashboard() {
    AdminDashBoard(
        onCreateStaffAccount = {},
        onCreateEnrollStudent = {},
        onViewAttendance = {}
    )
}