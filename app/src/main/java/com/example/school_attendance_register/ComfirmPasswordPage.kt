package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.ui.components.PageWithBackArrow

@Composable
fun ConfirmPasswordPage(navController: NavController) {

    var createPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Create Password") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Password",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
            )

            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Create Password Field
            TextField(
                value = createPass,
                onValueChange = { createPass = it },
                label = { Text("Create Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Confirm Password Field
            TextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Display Error Message if Passwords Do Not Match
            errorMessage?.let {
                Text(
                    it,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("Create_Account_Page") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Back",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        if (createPass != confirmPass) {
                            errorMessage = "Passwords do not match"
                        } else {
                            errorMessage = null
                            // Navigate to the Admin Dashboard
                            navController.navigate("Admin_Dash_Board")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Finish",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                }
            }
        }
    }
}
