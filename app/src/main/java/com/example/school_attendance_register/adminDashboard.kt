package com.example.school_attendance_register

// AdminDashboardScreen.kt

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AdminDashboard(
    onCreateStaffAccount: () -> Unit,
    onCreateEnrollStudent: () -> Unit,
    onViewAttendance: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding around the entire screen
        verticalArrangement = Arrangement.Center, // Center items vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center items horizontally
    ) {
        Text(
            text = "Admin Dashboard",
            style = MaterialTheme.typography.headlineSmall ,
            textAlign = TextAlign.Center
        )

        // Button to create a teacher account
        Button(
            onClick = onCreateStaffAccount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "Register Staff")
        }

        // Button to create student account
        Button(
            onClick = onCreateEnrollStudent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "Enroll Student")
        }

        // Button to view records
        Button(
            onClick = onViewAttendance,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "View Attendance")
        }

    }
}
//shows on screen as a preview
@Preview(showBackground = true)
@Composable
fun PreviewAdminDashboard() {
    AdminDashboard(
        onCreateStaffAccount = {},
        onCreateEnrollStudent = {},
        onViewAttendance = {}
    )
}