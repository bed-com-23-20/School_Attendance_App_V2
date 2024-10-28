package com.example.school_attendance_register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
    var password by remember { mutableStateOf("") }

    // Validation checks
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
        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Full Name Input
        TextField(
            value = adminFullName,
            onValueChange = { adminFullName = it },
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // School Name Input
        TextField(
            value = schoolName,
            onValueChange = { schoolName = it },
            label = { Text("School Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // District Input
        TextField(
            value = district,
            onValueChange = { district = it },
            label = { Text("District") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Phone Number Input
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Email Input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password Input
        TextField(
            value = createPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { createPass = it },
            label = { Text("Create Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Confirm Password Input
        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Button Row (Cancel and Submit)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 30.dp)
                .align(Alignment.CenterHorizontally)
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
            Spacer(modifier = Modifier.width(50.dp))
            Button(
                onClick = {
                    when {
                        createPass != password -> {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                        }
                        adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty() &&
                                phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty() && password.isNotEmpty() -> {
                            val adminInfo = AdminInfo(
                                adminFullName.uppercase(Locale.ROOT),
                                schoolName, district, phoneNumber.toInt(), email, createPass, password
                            )
                            myRef.child(adminFullName).setValue(adminInfo).addOnSuccessListener {
                                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                                navController.navigate("Admin_Dash")
                            }.addOnFailureListener {
                                Toast.makeText(context, "Account Creation Failed", Toast.LENGTH_LONG).show()
                            }
                        }
                        else -> Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
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
