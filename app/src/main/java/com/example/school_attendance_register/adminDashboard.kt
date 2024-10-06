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
    onCreateTeacherAccount: () -> Unit,
    onCreateStudentAccount: () -> Unit,
    onViewRecords: () -> Unit
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
            onClick = onCreateTeacherAccount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "Create Teacher Account")
        }

        // Button to create student account
        Button(
            onClick = onCreateStudentAccount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "Create Student Account")
        }

        // Button to view records
        Button(
            onClick = onViewRecords,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Vertical padding for space between buttons
        ) {
            Text(text = "View Records")
        }
    }
}
//shows on screen
@Preview(showBackground = true)
@Composable
fun PreviewAdminDashboard() {
    AdminDashboard(
        onCreateTeacherAccount = {},
        onCreateStudentAccount = {},
        onViewRecords = {}
    )
}