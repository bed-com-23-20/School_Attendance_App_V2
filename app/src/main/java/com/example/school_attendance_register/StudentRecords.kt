package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.school_attendance_register.data_classes.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.compose.material3.Card
import androidx.compose.material3.Text


@Composable
fun StudentRecords(navController: NavController) {
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")

    // State for the student list
    var studentList by remember { mutableStateOf<List<StudentInfo>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    // Fetch students from Firebase in real-time
    LaunchedEffect(Unit) {
        myRefStudent.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val students = mutableListOf<StudentInfo>()
                dataSnapshot.children.forEach { snapshot ->
                    val student = snapshot.getValue(StudentInfo::class.java)
                    student?.let { students.add(it) }
                }
                studentList = students
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch students.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    val filteredList = studentList.filter {
        it.fname.contains(searchQuery, ignoreCase = true) ||
                it.sname.contains(searchQuery, ignoreCase = true) ||
                it.uniqueId.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(navController = navController, title = "Student Records")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Name or ID") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredList.isEmpty()) {
                Text(text = "No records found.", color = Color.Gray)
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    filteredList.forEach { student ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)

                        ) {
                            Column (modifier = Modifier.padding(16.dp)) {
                                Text(text = "Name: ${student.fname} ${student.sname}")
                                Text(text = "Guardian: ${student.guardianName}")
                                Text(text = "Class: ${student.classform}")
                                Text(text = "DOB: ${student.dateOfBirth}")
                                Text(text = "Gender: ${student.gender}")
                                Text(text = "ID: ${student.uniqueId}")
                            }
                        }
                    }
                }
            }
        }
    }
}
