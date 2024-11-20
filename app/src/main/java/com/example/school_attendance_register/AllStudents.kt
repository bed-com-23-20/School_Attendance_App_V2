package com.example.school_attendance_register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.school_attendance_register.data_classes.StudentInfo

@Composable
fun AllStudents(result: String, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBarWithBack(navController = navController, title = "ALL STUDENTS")
        }
    ) { innerPadding ->

        // Parse student details from result
        val studentList = result.split("------------------------------------------------------------------------")
            .filter { it.isNotBlank() } // Filter out empty entries
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

                // Create a StudentInfo object for each entry
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

        // Log parsed student list size
        Log.d("StudentListSize", "Size: ${studentList.size}")

        // UI Content
        if (studentList.isEmpty()) {
            // Display a fallback message if no students are available
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
            // Display list of students
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
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            // Display other student details
                            Text("Unique Code: ${student.uniqueId}", style = MaterialTheme.typography.bodySmall)
                            Text("Guardian Name: ${student.guardianName}", style = MaterialTheme.typography.bodySmall)
                            Text("Guardian Contact: ${student.guardianPhone}", style = MaterialTheme.typography.bodySmall)
                            Text("Gender: ${student.gender}", style = MaterialTheme.typography.bodySmall)
                            Text("Date of Birth: ${student.dateOfBirth}", style = MaterialTheme.typography.bodySmall)
                            Text("Class: ${student.classform}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}



    // fun AllStudentsScreen(navController: NavController, result: String) {
//        Scaffold(
//            topBar = {
//                TopAppBarWithBack(navController = navController, title = "All Students")
//            }
//        ) { innerPadding ->
//            // Parse the `result` string into a list of student objects
//            val studentList = result.split("-----------------------------------------------------------------------------")
//                .mapNotNull { entry ->
//                    val lines = entry.trim().split("\n")
//                    if (lines.size >= 7) {
//                        // Extract details from each line
//                        val firstName = lines.getOrNull(0)?.substringAfter("First Name: ")?.trim() ?: ""
//                        val surname = lines.getOrNull(1)?.substringAfter("Surname: ")?.trim() ?: ""
//                        val uniqueCode = lines.getOrNull(2)?.substringAfter("Unique Code: ")?.trim() ?: ""
//                        val parentContact = lines.getOrNull(3)?.substringAfter("Guardian Name: ")?.trim() ?: ""
//                        val gender = lines.getOrNull(4)?.substringAfter("Gender: ")?.trim() ?: ""
//                        val dob = lines.getOrNull(5)?.substringAfter("Date of Birth: ")?.trim() ?: ""
//                        val classGrade = lines.getOrNull(6)?.substringAfter("Class: ")?.trim() ?: ""
//                        val guardianName = lines.getOrNull(7)?.substringAfter("Guardian Name: ")?.trim() ?: ""
//                        StudentInfo(
//                            fname = firstName,
//                            sname = surname,
//                            uniqueId = uniqueCode,
//                            guardianPhone = parentContact,
//                            gender = gender,
//                            dateOfBirth = dob,
//                            guardianName = guardianName,
//                            classform = classGrade
//                        )
//                    } else null
//                }
//
//            if (studentList.isEmpty()) {
//                // Display a fallback message if no students are available
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(innerPadding),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "No students found.",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier
//                        .padding(innerPadding)
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    items(studentList) { student ->
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight(),
//                            elevation = CardDefaults.cardElevation(8.dp),
//                            shape = MaterialTheme.shapes.medium,
//                            colors = CardDefaults.cardColors(
//                                containerColor = MaterialTheme.colorScheme.surface
//                            )
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp),
//                                verticalArrangement = Arrangement.spacedBy(8.dp)
//                            ) {
//                                // Student Full Name
//                                Text(
//                                    text = "${student.fname} ${student.sname}",
//                                    style = MaterialTheme.typography.titleMedium,
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
//
//                                // Additional Details
//                                Text("Unique Code: ${student.uniqueId}", style = MaterialTheme.typography.bodySmall)
//                                Text("Parent Contact: ${student.guardianPhone}", style = MaterialTheme.typography.bodySmall)
//                                Text("Gender: ${student.gender}", style = MaterialTheme.typography.bodySmall)
//                                Text("Date of Birth: ${student.dateOfBirth}", style = MaterialTheme.typography.bodySmall)
//                                Text("Class: ${student.classform}", style = MaterialTheme.typography.bodySmall)
//                                Text("Class: ${student.guardianName}", style = MaterialTheme.typography.bodySmall)
//                            }
//                        }
//                    }
//                }
//            }
//        }



//    Scaffold(
//        topBar = {
//            TopAppBarWithBack(navController = navController, title = "All Students")
//        }
//    ) { innerPadding ->
//        // Ensure the result is parsed correctly
//        val studentNames = result
//            .split("\n")
//            .mapNotNull { line ->
//                if (line.contains("First Name =")) {
//                    line.substringAfter("First Name =").trim()
//                } else null
//            }
//
//
//        if (studentNames.isEmpty()) {
//            // Display a fallback message if no students are available
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "No students found.",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(studentNames) { studentName ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        elevation = CardDefaults.cardElevation(8.dp),
//                        shape = MaterialTheme.shapes.medium,
//                        colors = CardDefaults.cardColors(
//                            containerColor = MaterialTheme.colorScheme.surface
//                        )
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            // Student Details
//                            Column(modifier = Modifier.weight(1f)) {
//                                Text(
//                                    text = studentName,
//                                    style = MaterialTheme.typography.titleMedium,
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
//                                Spacer(modifier = Modifier.height(4.dp))
//                                Text(
//                                    text = "More details available upon selection",
//                                    style = MaterialTheme.typography.bodySmall,
//                                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                                )
//                            }
//
//                            // Icon
//                            Icon(
//                                imageVector = Icons.Default.Person,
//                                contentDescription = "Student Icon",
//                                modifier = Modifier.size(40.dp),
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
    //}




//////////////////////////////////////////////////////////////////////////////////////
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

