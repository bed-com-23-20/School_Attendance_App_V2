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
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {

            val scrollState = rememberScrollState()
            //Calling the LandingPage function
            val navController = rememberNavController()
            NavHost(navController = navController,  startDestination = "Landing_Page", builder = {


                composable("Landing_Page"){
                    LandingPage(navController)
                }
                composable("Login_Page",){
                    LoginPage(navController, AuthViewModel())
                }

                composable("Create_Account_Page",){
                    CreateAccount(navController)
                }

                composable("Admin_Dash_Board"){
                    AdminDashBoard(navController)
                }
                composable("Student_Enroll") {
                    EnrollStudent(navController)
                }

                composable("Register_Staff") {
                    RegisterStaff(navController)
                }
                composable("Mark_Attendance"){
                    MarkAttendance(navController)
                }
                composable("allStudents/{result}") { backStackEntry ->
                    val result = backStackEntry.arguments?.getString("result") ?: ""
                    AllStudents(result, navController)
                }


            }
            )

        }
    }
}

