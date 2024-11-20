package com.example.school_attendance_register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.StaffInfo
import com.example.school_attendance_register.ui.components.PageWithBackArrow
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegisterStaff(navController: NavController) {
    // State variables for input fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var className by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance()
    val myStaff = database.getReference("Staff")

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Register Staff") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register Staff",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = className,
                onValueChange = { className = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    Log.d("ButtonClick", "Register button clicked")
                    if (email.isEmpty() || name.isEmpty() || phone.isEmpty() || className.isEmpty()) {
                        Toast.makeText(context, "The fields cannot be empty", Toast.LENGTH_SHORT).show()
                        Log.d("Validation", "One or more fields are empty")
                    } else {
                        Log.d("Validation", "All fields are filled")
                        val staffInfo = StaffInfo(name, email, phone, className)

                        myStaff.child(name).setValue(staffInfo).addOnSuccessListener {
                            Log.d("FirebaseSuccess", "Staff added successfully")
                            name = ""
                            email = ""
                            phone = ""
                            className = ""
                            Toast.makeText(context, "The staff has been added", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener { exception ->
                            Log.e("FirebaseError", "Failed to add staff: ${exception.message}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Display feedback message
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (message.contains("successful")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
