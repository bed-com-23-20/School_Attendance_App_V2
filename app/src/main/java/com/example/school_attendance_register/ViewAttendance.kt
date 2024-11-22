package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.data_classes.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllAttendance(navController: NavController) {
    val database = FirebaseDatabase.getInstance()
    val attendanceRef = database.getReference("Attendance")
    val context = LocalContext.current

    var attendanceList by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    // Fetch attendance data
    LaunchedEffect(Unit) {
        attendanceRef.get().addOnSuccessListener { snapshot ->
            val fetchedList = mutableListOf<Map<String, String>>()
            for (child in snapshot.children) {
                val studentName = child.child("studentName").getValue(String::class.java) ?: ""
                val classGrade = child.child("classGrade").getValue(String::class.java) ?: ""

                if (studentName.isNotEmpty() && classGrade.isNotEmpty()) {
                    fetchedList.add(mapOf("studentName" to studentName, "classGrade" to classGrade))
                }
                //fetchedList.add(mapOf("studentName" to studentName, "classGrade" to classGrade))
            }
            attendanceList = fetchedList
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Failed to load data: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = "ATTENDANCE",
                backButtonColor = Color.Red,
                backIconColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Name or class. eg 3") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Filtered list based on search query
            val filteredList = attendanceList.filter {
                it["studentName"]?.contains(searchQuery, ignoreCase = true) == true ||
                        it["classGrade"]?.contains(searchQuery, ignoreCase = true) == true
            }


            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredList) { student ->
                    AttendanceCard(studentName = student["studentName"] ?: "", classGrade = student["classGrade"] ?: "")
                }
            }
        }
    }
}
@Composable
fun AttendanceCard(studentName: String, classGrade: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Student Name: $studentName", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Class Grade: $classGrade", style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
        }
    }
}
