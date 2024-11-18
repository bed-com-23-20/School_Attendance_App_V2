package com.example.school_attendance_register.plastol_pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.AdminInfo
import com.example.school_attendance_register.ui.components.PageWithBackArrow
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale
import java.util.regex.Pattern

@SuppressLint("SuspiciousIndentation")
@Composable
fun CreateAccount(navController: NavController) {
    // State variables
    var adminFullName by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var createPass by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    // Firebase Database Reference
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")
    val emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    val minLength = 6
    val context = LocalContext.current

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Create Account") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Account",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            // Input Fields
            Spacer(modifier = Modifier.height(10.dp))
            InputField(value = adminFullName, label = "Full Name") { adminFullName = it }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(value = schoolName, label = "School Name") { schoolName = it }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(value = district, label = "District") { district = it }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(value = phoneNumber, label = "Phone Number", keyboardType = KeyboardType.Phone) { phoneNumber = it }

            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    isEmailError = !emailPattern.matcher(it).matches()
                },
                label = { Text("Email") },
                isError = isEmailError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            if (isEmailError) {
                Text(
                    text = "Please enter a valid email address",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(value = createPass, label = "Create Password", minLength) { input ->
                createPass = input
                isError = input.length < minLength
            }
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(value = password, label = "Confirm Password") { password = it }

            if (isError) {
                Text(
                    text = "Password must be at least $minLength characters",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("Login_Page") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Cancel", color = Color.White)
                }

                Button(
                    onClick = {
                        if (createPass != password) {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                        } else if (adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty() &&
                            phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty()) {

                            val encodedEmail = encodeEmail(email)
                            val adminInfo = AdminInfo(
                                adminFullName.uppercase(Locale.ROOT), schoolName, district,
                                phoneNumber.toInt(), encodedEmail, createPass, password
                            )

                            myRef.child(encodedEmail).setValue(adminInfo).addOnSuccessListener {
                                Toast.makeText(context, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show()
                                navController.navigate("Admin_Dash_Board")
                            }.addOnFailureListener {
                                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(context, "Please fill all fields before submitting", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Submit", color = Color.White)
                }
            }
        }
    }
}

// Helper function to encode email
fun encodeEmail(email: String): String {
    return email.replace(".", ",")
}

// Custom Input Field
@Composable
fun InputField(value: String, label: String, keyboardType: KeyboardType = KeyboardType.Text, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}

// Custom Password Field
@Composable
fun PasswordField(value: String, label: String, minLength: Int = 6, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        isError = value.length < minLength,
        modifier = Modifier.fillMaxWidth()
    )
}
