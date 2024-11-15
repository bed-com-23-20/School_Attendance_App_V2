package com.example.school_attendance_register

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.school_attendance_register.ui.theme.SchoolAttendanceRegisterTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            SchoolAttendanceRegisterTheme {
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
    }
}
