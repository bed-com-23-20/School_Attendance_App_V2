package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AdminDashBoard(navController: NavController) {


    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = "   ADMIN",
                backButtonColor = Color.Red,
                backIconColor = Color.White
            )
        }
    ) {paddingValues ->


    Column(
        modifier = Modifier
            .fillMaxSize() // Take up full available space
            .padding(16.dp)
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))


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
                onClick = {
                    navController.navigate("Register_Staff")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "Register Staff")
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set the button's container color to black
                    contentColor = Color.White // Set the text color to white for contrast
                ),
                onClick = {
                    navController.navigate("ViewStaffs")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "View All Staffs")
            }


            // Button to create student account
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,  // Set the button's container color to black
                    contentColor = Color.White     // Set the text color to white for contrast
                ),
                onClick = {
                    navController.navigate("Student_Enroll")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "Enroll Student")
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set the button's container color to black
                    contentColor = Color.White // Set the text color to white for contrast
                ),
                onClick = {
                    navController.navigate("AllSTD")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "View All Student")
            }


            // Button to view attendance records
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Set the button's container color to black
                    contentColor = Color.White // Set the text color to white for contrast
                ),
                onClick = { navController.navigate("ViewAttendace")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Vertical padding for space between buttons
            ) {
                Text(text = "View Attendance")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }   }
}
