package com.example.school_attendance_register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AllStudents(result: String, navController: NavController) {


        Scaffold(
            topBar = {
                TopAppBarWithBack(navController, title = "All Students")
            }
        ) { innerPadding ->
            // Parse the `result` into a list of student names
            val studentNames = result.split("\n")
                .filter { it.isNotBlank() && it.startsWith("Student Name = ") }
                .map { it.removePrefix("Student Name = ").trim() }

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(studentNames) { studentName ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Student Details
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = studentName,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "More details available upon selection",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Icon
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Student Icon",
                                modifier = Modifier.size(40.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }


//    Scaffold(
//        topBar = {
//            TopAppBarWithBack(navController = navController, title = "Mark Attendance",
//                backButtonColor = Color.Red,
//                backIconColor = Color.White
//            )
//        }
//    ) { paddingValues ->
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 50.dp, bottom = 20.dp)
//                .padding(paddingValues),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        )
//        {
//            Text(
//                text = "ENROLLED STUDENTS",
//                fontSize = 28.sp,
//                fontFamily = FontFamily.Serif,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier
//                    .background(color = Color.White)
//
//            )
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//
//        val students =
//            result.split("------------------------------------------------------------------------")
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(start = 13.dp, top = 70.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.Start
//        ) {
//
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(students) { student ->
//                    Text(
//                        text = student,
//                        fontSize = 16.sp,
//                        color = Color.Black,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                    )
//                    Divider(color = Color.Gray)
//                }
//            }
//        }
//
//    }
//}

