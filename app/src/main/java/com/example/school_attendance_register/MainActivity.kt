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

            //Calling the LandingPage function
            val navController = rememberNavController()
            NavHost(navController = navController,  startDestination = "Landing_Page", builder = {
                composable("Landing_Page"){
                    LandingPage(navController)
                }
                composable("Login_Page",){
                    LoginPage(navController)
                }

                composable("Create_Account_Page",){
                    CreateAccount(navController)
                }

                composable("Confirm_Password_page",){
                    ComfirmPasswordPage(navController)
                }
                composable("Admin_Dash_Board") {
                    AdminDashBoard(
                        onCreateStaffAccount = { navController.navigate("Create_Account_Page") },
                        onCreateEnrollStudent = { navController.navigate("Create_Account_Page") }, // Or any other page for enrolling students
                        onViewAttendance = { navController.navigate("View_Attendance_Page") }  // Create and define this route
                    )
                }



            }
            )

        }
    }
}

