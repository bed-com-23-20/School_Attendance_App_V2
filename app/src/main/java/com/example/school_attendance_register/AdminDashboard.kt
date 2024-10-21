package com.example.school_attendance_register

// AdminDashboardScreen.kt

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AdminDashBoard(
    navController: NavController,
    onCreateStaffAccount: () -> Unit,
    onCreateEnrollStudent: () -> Unit,
    onViewAttendance: () -> Unit
) {
    // Create a column to hold the entire admin dashboard
    Column(
        modifier = Modifier
            .fillMaxSize() // Take up full available space
            .padding(16.dp),
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        // Admin Dashboard heading at the top
        Text(
            text = "ADMIN DASHBOARD",
            fontSize = 28.sp, // Set the font size
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp) // Add some space below the heading
        )

        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp) // Add padding for the divider
        )

        Spacer(modifier = Modifier.weight(1f)) // Space between the divider and buttons

        // Buttons centered vertically
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center, // Center items vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center items horizontally
        ) {
            // Button to create a teacher account
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set the button's container color to black
                    contentColor = Color.White // Set the text color to white for contrast
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
                onClick = {
                    navController.navigate("Enroll_Student") // Navigate to EnrollStudent screen
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "Enroll Student")
            }


            // Button to view attendance records
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set the button's container color to black
                    contentColor = Color.White // Set the text color to white for contrast
                ),
                onClick = {
                    navController.navigate("View_Attendance") // Navigate to ViewAttendance screen
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "View Attendance")
            }
        }

        // Spacer to push content to the bottom if needed
        Spacer(modifier = Modifier.weight(1f))
    }
}


//shows on screen as a preview
@Preview(showBackground = true)
@Composable
fun PreviewAdminDashboard() {
    // Create a mock NavController
    val navController = rememberNavController()

    // Call AdminDashBoard with the mock NavController
    AdminDashBoard(
        navController = navController, // Pass the mock NavController
        onCreateStaffAccount = {},
        onCreateEnrollStudent = {},
        onViewAttendance = {}
    )
}
