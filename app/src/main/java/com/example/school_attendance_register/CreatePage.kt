package com.example.school_attendance_register

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
import java.util.*

@Composable
fun CreatePage(navController: NavController) {
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )
        Divider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = adminFullName,
            onValueChange = { adminFullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = schoolName,
            onValueChange = { schoolName = it },
            label = { Text("School Name") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = district,
            onValueChange = { district = it },
            label = { Text("District") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = createPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { createPass = it },
            label = { Text("Create Password") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = confirmPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { confirmPass = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("Login_Page")
                },
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
                    // Validation checks for creating the admin account
                    if (createPass != confirmPass) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    } else if (adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty() &&
                        phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty() && confirmPass.isNotEmpty()
                    ) {
                        // Validate phone number as numeric
                        if (phoneNumber.all { it.isDigit() }) {
                            // Save admin info to the database
                            val adminInfo = AdminInfo(
                                adminFullName.toUpperCase(Locale.ROOT),
                                schoolName,
                                district,
                                phoneNumber.toInt(),
                                email,
                                createPass,
                                confirmPass
                            )
                            myRef.child(adminFullName).setValue(adminInfo).addOnSuccessListener {
                                // Clear fields on success
                                adminFullName = ""
                                schoolName = ""
                                district = ""
                                phoneNumber = ""
                                email = ""
                                createPass = ""
                                confirmPass = ""

                                Toast.makeText(
                                    context,
                                    "Admin Account Created Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate("Admin_DashBoard")
                            }.addOnFailureListener {
                                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(context, "Phone number must be numeric", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill in all the fields",
                            Toast.LENGTH_SHORT
                        ).show()
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
