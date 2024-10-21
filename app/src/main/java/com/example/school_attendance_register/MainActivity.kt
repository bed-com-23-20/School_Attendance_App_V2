package com.example.school_attendance_register

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Landing_Page") {
                composable("Landing_Page") {
                    LandingPage(navController)
                }
                composable("Login_Page") {
                    LoginPage(navController)
                }
                composable("Create_Account_Page") {
                    CreateAccount(navController)
                }
                composable("Confirm_Password_page") {
                    ComfirmPasswordPage(navController)
                }
                composable("Admin_Dash_Board") {
                    AdminDashBoard(
                        navController = navController, // Pass the NavController
                        onCreateStaffAccount = { /* Handle creating staff account logic here */ },
                        onCreateEnrollStudent = { navController.navigate("enroll_student") },
                        onViewAttendance = { navController.navigate("View_Attendance") } // Ensure this route is defined
                    )
                }
                composable("Register_Staff") {
                    RegisterStaff(
                        onRegister = { fname, sname, phone, email, className ->
                            // Handle the registration logic here
                        },
                        onBack = { navController.popBackStack() } // Navigate back to home screen
                    )
                }
                composable("Enroll_Student") {
                    EnrollStudent(
                        onEnroll = { fname, sname, guardianName, guardianPhone, className, dateOfBirth, gender ->
                            // Handle enrollment logic
                        },
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("View_Attendance") {
                    ViewAttendance(onBack = { navController.popBackStack() })
                }
            }
        }
    }
}
