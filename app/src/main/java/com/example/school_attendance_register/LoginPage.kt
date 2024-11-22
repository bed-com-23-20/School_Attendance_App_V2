
package com.example.school_attendance_register

import android.util.Log
//import androidx.compose.foundation.text.PasswordVisualTransformation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.*
import androidx.compose.runtime.*
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.regex.Pattern

@Composable
//@Preview(showBackground = true)
fun LoginPage(navController: NavController, viewModel: AuthViewModel<Any?>){


    var email by remember { mutableStateOf("") }
    var encodedEmail = encodeEmail(email)
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var isEmailError by remember { mutableStateOf(false) }
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
    )


    var loading by remember { mutableStateOf(false) }
    val error by remember { mutableStateOf<String?>(null) }
    var isLoggedIn by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = "ADMIN LOGIN ",
                backButtonColor = Color.Red,
                backIconColor = Color.White
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.adminloginvector),
                contentDescription = "Admin vector"
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { input ->
                    email = input
                    isEmailError = !emailPattern.matcher(input).matches()
                },


                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Username") },
                placeholder = { Text("Enter your Username or Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
//                    .background(
//                        color = Color.White,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                shape = RoundedCornerShape(24.dp),
                isError = isEmailError

            )
            if (isEmailError) {
                Text(
                    text = "Please enter a valid email address",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)

            )



//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                placeholder = { Text("Enter your password") },
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                trailingIcon = {
//                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
//                    val description = if (passwordVisible) "Hide password" else "Show password"
//
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(imageVector = image, contentDescription = description)
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            )

//            OutlinedTextField(
//                value = password,
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp)
//                    .background(
//                        color = Color.White,
//                        shape = RoundedCornerShape(24.dp)
//                    ),
//                shape = RoundedCornerShape(24.dp)
//            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                    loading = true

                    if (password.isEmpty() && email.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Email and Password cant be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        loading = false
                    } else {
                        viewModel.fetchUserCredentials(email) { result ->
                            result.fold(
                                onSuccess = { credentials ->

                                    password = credentials.second
                                    navController.navigate("Admin_Dash_Board")
                                },
                                onFailure = { exception ->
                                    errorMessage = exception.message
                                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT)
                                        .show()
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
                    if (loading) "Logging in..." else "Login",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            error?.let {
                Text("Error: $it", color = Color.Red)
            }


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Not yet an Admin?, Become one",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic

            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {

                    navController.navigate("Create_Account_Page")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ) {
                Text(
                    text = "Create Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif


                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("Landing_Page")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ) {
                Text(
                    text = "Home",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif


                )
            }
        }
    }
}


