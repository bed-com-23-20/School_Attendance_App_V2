package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.data_classes.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSTD(navController: NavController) {

    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")
    val context = LocalContext.current

    // State variables
    var students by remember { mutableStateOf<List<StudentInfo>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = "ALL STUDENT",
                backIconColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("Student_Enroll")
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student")

            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->

        // Fetch data from Firebase
        LaunchedEffect(Unit) {
            myRefStudent.get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot.exists()) {
                        val fetchedStudents = snapshot.children.mapNotNull { it.toStudent() }
                        students = fetchedStudents
                    } else {
                        Toast.makeText(context, "No Students found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Failed to fetch students: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 0.dp),
                label = { Text("Search Students") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable List of Cards
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                val filteredStudents = students.filter {
                    it.fname.contains(searchQuery, ignoreCase = true) ||
                            it.sname.contains(searchQuery, ignoreCase = true)
                }

                items(filteredStudents) { student ->
                    StudentCard(student, navController)
                }
            }
        }

    }
    }

    @Composable
    fun StudentCard(student: StudentInfo, navController: NavController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { navController.navigate("viewStudent/${student.uniqueId}") },
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Full Name: ${student.fname} ${student.sname}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 20.sp
                )
                Text(
                    "Student Code: ${student.uniqueId}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    text = "Guardian Name: ${student.guardianName}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = "Guardian Contact: ${student.guardianPhone}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    "Gender: ${student.gender}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    "Date of Birth: ${student.dateOfBirth}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )

            }
        }
    }


    // Extension function to parse a student from a Firebase snapshot
    fun DataSnapshot.toStudent(): StudentInfo? {
        return try {
            StudentInfo(
                fname = child("fname").value?.toString().orEmpty(),
                sname = child("sname").value?.toString().orEmpty(),
                uniqueId = child("uniqueId").value?.toString().orEmpty(),
                guardianName = child("guardianName").value?.toString().orEmpty(),
                guardianPhone = child("guardianPhone").value?.toString().orEmpty(),
                classform = child("classform").value?.toString().orEmpty(),
                dateOfBirth = child("dateOfBirth").value?.toString().orEmpty(),
                gender = child("gender").value?.toString().orEmpty()
            )
        } catch (e: Exception) {
            null // Return null if parsing fails
        }
    }