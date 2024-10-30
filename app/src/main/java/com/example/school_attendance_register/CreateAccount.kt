package com.example.school_attendance_register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun CreateAccount(navController: NavController) {

    // Database Connection
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")

    // Admin Information
    var adminFullName by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var createPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )

        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        // Admin Information Input Fields
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = adminFullName,
            onValueChange = { adminFullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = schoolName,
            onValueChange = { schoolName = it },
            label = { Text("School Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = district,
            onValueChange = { district = it },
            label = { Text("District") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = createPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { createPass = it },
            label = { Text("Create Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = confirmPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { confirmPass = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Action Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("Login_Page") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            Button(
                onClick = {
                    // Input Validation
                    if (createPass != confirmPass) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    } else if (adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty()
                        && phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty()
                    ) {
                        val phoneInt = phoneNumber.toIntOrNull()
                        if (phoneInt == null) {
                            Toast.makeText(context, "Invalid phone number", Toast.LENGTH_SHORT).show()
                        } else {
                            // Save Admin Info to Database
                            val adminInfo = AdminInfo(
                                adminFullName.toUpperCase(Locale.ROOT),
                                schoolName,
                                district,
                                phoneInt,
                                email,
                                createPass,
                                confirmPass = confirmPass  // Add this line if `confirmPass` is required
                            )

                            myRef.child(adminFullName).setValue(adminInfo).addOnSuccessListener {
                                // Reset fields after successful creation
                                adminFullName = ""
                                schoolName = ""
                                district = ""
                                phoneNumber = ""
                                email = ""
                                createPass = ""
                                confirmPass = ""
                                Toast.makeText(context, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show()
                                navController.navigate("Admin_Dash_Board")
                            }.addOnFailureListener {
                                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Submit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}
