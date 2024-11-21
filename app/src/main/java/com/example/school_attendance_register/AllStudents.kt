package com.example.school_attendance_register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.data_classes.StudentInfo

@Composable
fun AllStudents(result: String, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController,
                title = " ALL STUDENTS",
                backButtonColor = Color.Red
            )
        }
    ) { innerPadding ->

        // Parse student details from result
        val studentList = result.split("------------------------------------------------------------------------")
            .filter { it.isNotBlank() }
            .map { entry ->
                val lines = entry.trim().split("\n")
                val firstName = lines.getOrNull(0)?.substringAfter("First Name = ")?.trim() ?: ""
                val surname = lines.getOrNull(1)?.substringAfter("Surname  = ")?.trim() ?: ""
                val uniqueCode = lines.getOrNull(2)?.substringAfter("Unique Code  = ")?.trim() ?: ""
                val guardianName = lines.getOrNull(3)?.substringAfter("Guardian Name = ")?.trim() ?: ""
                val guardianContact = lines.getOrNull(4)?.substringAfter("Guardian Contact = ")?.trim() ?: ""
                val classGrade = lines.getOrNull(5)?.substringAfter("Class = ")?.trim() ?: ""
                val dob = lines.getOrNull(6)?.substringAfter("Date of Birth = ")?.trim() ?: ""
                val gender = lines.getOrNull(7)?.substringAfter("Gender = ")?.trim() ?: ""


                StudentInfo(
                    fname = firstName,
                    sname = surname,
                    uniqueId = uniqueCode,
                    guardianName = guardianName,
                    guardianPhone = guardianContact,
                    classform = classGrade,
                    dateOfBirth = dob,
                    gender = gender
                )
            }


        Log.d("StudentListSize", "Size: ${studentList.size}")


        if (studentList.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No students found.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(studentList) { student ->
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Display Student Full Name
                            Text(
                                text = "${student.fname} ${student.sname}",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            // Display other student details
                            Text("Unique Code: ${student.uniqueId}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                            Text("Guardian Name: ${student.guardianName}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                            Text("Guardian Contact: ${student.guardianPhone}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                            Text("Gender: ${student.gender}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                            Text("Date of Birth: ${student.dateOfBirth}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                            Text("Class: ${student.classform}", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}
