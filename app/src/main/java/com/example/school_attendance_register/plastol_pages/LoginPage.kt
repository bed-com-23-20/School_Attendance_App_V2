package com.example.school_attendance_register.plastol_pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.R
import com.example.school_attendance_register.ui.components.PageWithBackArrow
import java.util.regex.Pattern

@Composable
fun LoginPage(navController: NavController, viewModel: AuthViewModel<Any?>) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    val emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Admin Login") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ADMIN LOGIN PAGE",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )

            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.adminloginvector),
                contentDescription = "Admin vector"
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = { input ->
                    email = input
                    isEmailError = !emailPattern.matcher(input).matches()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    ),
                shape = RoundedCornerShape(24.dp),
                isError = isEmailError
            )
            if (isEmailError) {
                Text(
                    text = "Please enter a valid email address",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Password Input Field
            OutlinedTextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    ),
                shape = RoundedCornerShape(24.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Login Button
            Button(
                onClick = {
                    loading = true
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Email and Password can't be empty", Toast.LENGTH_SHORT).show()
                        loading = false
                    } else {
                        viewModel.fetchUserCredentials(email) { result ->
                            result.fold(
                                onSuccess = { credentials ->
                                    val fetchedPassword = credentials.second

                                    // Validate the entered password against the fetched password
                                    if (password == fetchedPassword) {
                                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                        loading = false
                                        navController.navigate("Admin_Dash_Board")
                                    } else {
                                        errorMessage = "Incorrect password"
                                        Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show()
                                        loading = false
                                    }
                                },
                                onFailure = { exception ->
                                    errorMessage = exception.message
                                    Toast.makeText(context, "User not found: ${exception.message}", Toast.LENGTH_SHORT).show()
                                    Log.e("LoginScreen", "Login error: ${exception.message}")
                                    loading = false
                                }
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = if (loading) "Logging in..." else "Login",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            }

            errorMessage?.let {
                Text("Error: $it", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Not yet an Admin? Become one",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(5.dp))

            // Create Account Button
            Button(
                onClick = { navController.navigate("Create_Account_Page") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Create Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Home Button
            Button(
                onClick = { navController.navigate("Landing_Page") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Home",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            }
        }
    }
}
