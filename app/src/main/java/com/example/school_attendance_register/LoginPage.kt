package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginPage(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    // Observe authentication state from ViewModel
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                loading = false
                navController.navigate("Admin_Dash_Board")
            }
            is AuthState.Error -> {
                loading = false
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            is AuthState.Unauthenticated -> {
                loading = false
                // Optional: Handle unauthenticated case if needed
            }
            null -> {
                loading = false
                // Optional: Handle null case if needed
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.adminloginvector),
            contentDescription = "Admin vector",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(24.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Login Button
        Button(
            onClick = {
                loading = true
                authViewModel.login(email, password)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = if (loading) "Logging in..." else "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        // Display Error Message
        error?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Error: $it", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(10.dp))

       //

        Spacer(modifier = Modifier.height(10.dp))

        // Create Account Button
        Button(
            onClick = { navController.navigate("Create_Account_Page") },
            modifier = Modifier.fillMaxWidth(),
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

        // Home Button
        Button(
            onClick = { navController.navigate("Landing_Page") },
            modifier = Modifier.fillMaxWidth(),
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
