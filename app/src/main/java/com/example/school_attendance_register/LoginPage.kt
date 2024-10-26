package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
//@Preview(showBackground = true)
fun LoginPage(navController: NavController, authViewModel: AuthViewModel){


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

//    val authState = authViewModel.authState.observeAsState()
//    val context = LocalContext.current
//
//    LaunchedEffect(authState.value) {
//        when(authState.value){
//            is AuthState.Authenticated -> navController.navigate("Admin_Dash_Board")
//            is AuthState.Error -> Toast.makeText(context,
//                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
//            else -> Unit
//        }
//    }

    //val authViewModel: AuthViewModel = viewModel()
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoggedIn by remember { mutableStateOf(false) }

//    if (isLoggedIn) {
//        // Show logged-in content
//        Text("Logged in successfully")
//    } else {
//        // Show login button
//        Button(
//            onClick = {
//                loading = true
//                authViewModel.fetchCredentials({ email, password ->
//                    authViewModel.login(email, password, {
//                        loading = false
//                        isLoggedIn = true
//                    }, { loginError ->
//                        loading = false
//                        error = loginError
//                    })
//                }, { fetchError ->
//                    loading = false
//                    error = fetchError
//                })
//            }
//        ) {
//            Text(if (loading) "Logging in..." else "Login")
//        }
//
//        error?.let {
//            Text("Error: $it", color = Color.Red)
//        }
//    }







    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally

    ) {
        Text(
            text = "ADMIN LOGIN PAGE",
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

        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.adminloginvector), contentDescription = "Admin vector"
            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Username")},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .background(
                      color = Color.White,
                      shape = RoundedCornerShape(24.dp)
                 ),
             shape = RoundedCornerShape(24.dp)

        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            //keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {password = it},
            label = {Text("Password")},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                ),
            shape = RoundedCornerShape(24.dp)


        )

        Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    loading = true
                    authViewModel.fetchCredentials({ email: String, password: String ->
                        authViewModel.login(email, password, {
                            loading = false
                            isLoggedIn = true
                        }, { loginError ->
                            loading = false
                            error = loginError
                        })
                    },
                        onError = { fetchError: String ->
                            loading = false
                            error = fetchError
                        }
                    )
                }




            ) {
                Text(if (loading) "Logging in..." else "Login",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            error?.let {
            Text("Error: $it", color = Color.Red)
            }
//
//            {
//                Text(
//                    text = "Login",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = FontFamily.Serif
//
//
//                )
//            }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
             text = "Not yet an Admin?, Become one",
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic

        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(onClick = {
            navController.navigate("Create_Account_Page")
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor  = Color.Black)

        ) {
            Text(
                text = "Create Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif


            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate("Landing_Page")
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor  = Color.Black)

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