package com.example.school_attendance_register

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge UI
        enableEdgeToEdge()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            // Set up navigation controller
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "Create_Account_Page"
            ) {
                // Landing Page
                composable("Landing_Page") {
                    LandingPage(navController)
                }

                // Login Page
                composable("Login_Page") {
                    LoginPage(navController, AuthViewModel())
                }

                // Create Account Page
                composable("Create_Account_Page") {
                    CreateAccount(navController)
                }

                // Admin Dashboard
                composable("Admin_Dash_Board") {
                    AdminDashBoard(navController)
                }

                // Student Enrollment
                composable("Student_Enroll") {
                    EnrollStudent(navController)
                }

                // Staff Registration
                composable("Register_Staff") {
                    RegisterStaff(navController)
                }

                // Mark Attendance
                composable("Mark_Attendance") {
                    MarkAttendance(navController)
                }

                // All Students with Result Argument
                composable("allStudents/{result}") { backStackEntry ->
                    val result = backStackEntry.arguments?.getString("result") ?: ""
                    AllStudents(result)
                }
            }
        }
    }
}
