package com.example.school_attendance_register.chikondi_pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun ViewAttendance(navController: NavController) {
    var studentList by remember { mutableStateOf(listOf<StudentInfo>()) }
    val database = FirebaseDatabase.getInstance().getReference("Admin")

    // Fetch student records from Firebase
    LaunchedEffect(Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val students = mutableListOf<StudentInfo>()
                snapshot.children.forEach { studentSnapshot ->
                    val student = studentSnapshot.getValue(StudentInfo::class.java)
                    if (student != null) {
                        students.add(student)
                    }
                }
                studentList = students
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Student Records",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(studentList) { student ->
                StudentRecordItem(student)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("Admin_Dash_Board") }) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun StudentRecordItem(student: StudentInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${student.fname} ${student.sname}")
            Text("Guardian: ${student.guardianName}")
            Text("Phone: ${student.guardianPhone}")
            Text("Class: ${student.classform}")
            Text("DOB: ${student.dateOfBirth}")
            Text("Gender: ${student.gender}")
        }
    }
}
