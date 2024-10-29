package com.example.school_attendance_register

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Landing_Page") {
                composable("Landing_Page") {
                    LandingPage(navController)
                }
                composable("Login_Page") {
                    LoginPage(navController, AuthViewModel())
                }
                composable("Create_Account_Page") {
                    CreateAccount(navController)
                }
                composable("Confirm_Password_page") {
                    ComfirmPasswordPage(navController)
                }
                composable("Admin_Dash_Board") {
                    AdminDashBoard(navController)
                }
                composable("Register_staff") {
                    RegisterStaff(navController)
                }
                composable("Mark_Attendance") {
                    MarkAttendance(navController)
                }
            }
        }
    }

    private fun ComfirmPasswordPage(navController: NavHostController) {
        TODO("Not yet implemented")
    }
}
