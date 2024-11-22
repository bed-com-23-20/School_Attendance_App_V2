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

    var searchQuery by remember { mutableStateOf("") }
    var groupedAttendance by remember { mutableStateOf<Map<String, List<Map<String, String>>>>(emptyMap()) }
    var filteredGroupedAttendance by remember { mutableStateOf<Map<String, List<Map<String, String>>>>(emptyMap()) }

    // Fetch attendance data
    LaunchedEffect(Unit) {
        attendanceRef.get().addOnSuccessListener { snapshot ->
            val groupedData = mutableMapOf<String, MutableList<Map<String, String>>>()

            for (dateSnapshot in snapshot.children) {
                val date = dateSnapshot.key ?: continue
                val records = mutableListOf<Map<String, String>>()

                for (child in dateSnapshot.children) {
                    val studentName = child.child("studentName").getValue(String::class.java) ?: ""
                    val classGrade = child.child("classGrade").getValue(String::class.java) ?: ""
                    if (studentName.isNotEmpty() && classGrade.isNotEmpty()) {
                        records.add(mapOf("studentName" to studentName, "classGrade" to classGrade))
                    }
                }
                groupedData[date] = records
            }
            groupedAttendance = groupedData
            filteredGroupedAttendance = groupedData // Initialize filtered data
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Failed to load data: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Update filtered attendance when search query changes
    LaunchedEffect(searchQuery, groupedAttendance) {
        if (searchQuery.isEmpty()) {
            filteredGroupedAttendance = groupedAttendance
        } else {
            val filteredData = groupedAttendance.mapValues { (_, records) ->
                records.filter { record ->
                    val nameMatch = record["studentName"]?.contains(searchQuery, ignoreCase = true) == true
                    val gradeMatch = record["classGrade"]?.contains(searchQuery, ignoreCase = true) == true
                    nameMatch || gradeMatch
                }
            }.filterValues { it.isNotEmpty() }
            filteredGroupedAttendance = filteredData
        }
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController,
                title = "ATTENDANCE",
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
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Name or Class Grade") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Reduced vertical padding
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                filteredGroupedAttendance.forEach { (date, records) ->
                    // Display date header
                    item {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    // Display list of attendance records for the date
                    items(records) { record ->
                        AttendanceCard(
                            studentName = record["studentName"] ?: "",
                            classGrade = record["classGrade"] ?: ""
                        )
                    }
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
            .padding(horizontal = 16.dp, vertical = 4.dp), // Adjusted padding for compactness
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Student Name: $studentName",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Class Grade: $classGrade",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp
            )
        }
    }
}




//@Composable
//fun ViewAllAttendance(navController: NavController) {
//    val database = FirebaseDatabase.getInstance()
//    val attendanceRef = database.getReference("Attendance")
//    val context = LocalContext.current
//
//    var filteredGroupedAttendance by remember { mutableStateOf<Map<String, List<Map<String, String>>>>(emptyMap()) }
//
//
//    var attendanceList by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
//    var searchQuery by remember { mutableStateOf("") }
//    var groupedAttendance by remember {
//        mutableStateOf<Map<String, List<Map<String, String>>>>(
//            emptyMap()
//        )
//    }
//
//    // Fetch attendance data
//
//    LaunchedEffect(Unit) {
//        attendanceRef.get().addOnSuccessListener { snapshot ->
//            val groupedData = mutableMapOf<String, MutableList<Map<String, String>>>()
//
//            for (dateSnapshot in snapshot.children) {
//                val date = dateSnapshot.key ?: continue
//                val records = mutableListOf<Map<String, String>>()
//
//                for (child in dateSnapshot.children) {
//                    val studentName = child.child("studentName").getValue(String::class.java) ?: ""
//                    val classGrade = child.child("classGrade").getValue(String::class.java) ?: ""
//                    if (studentName.isNotEmpty() && classGrade.isNotEmpty()) {
//                        records.add(mapOf("studentName" to studentName, "classGrade" to classGrade))
//                    }
//                }
//                groupedData[date] = records
//            }
//            groupedAttendance = groupedData
//        }.addOnFailureListener { exception ->
//            Toast.makeText(context, "Failed to load data: ${exception.message}", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    LaunchedEffect(searchQuery, groupedAttendance) {
//        if (searchQuery.isEmpty()) {
//            filteredGroupedAttendance = groupedAttendance
//        } else {
//            val filteredData = groupedAttendance.mapValues { (_, records) ->
//                records.filter { record ->
//                    val nameMatch = record["studentName"]?.contains(searchQuery, ignoreCase = true) == true
//                    val gradeMatch = record["classGrade"]?.contains(searchQuery, ignoreCase = true) == true
//                    nameMatch || gradeMatch
//                }
//            }.filterValues { it.isNotEmpty() }
//            filteredGroupedAttendance = filteredData
//        }
//    }
//
//
//
//    Scaffold(
//        topBar = {
//            TopAppBarWithBack(
//                navController = navController, title = "ATTENDANCE",
//                backButtonColor = Color.Red,
//                backIconColor = Color.White
//            )
//        }
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues)) {
//            // Search bar
//            OutlinedTextField(
//                value = searchQuery,
//                onValueChange = { searchQuery = it },
//                label = { Text("Search by Name or Class Grade") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//
//            LazyColumn(modifier = Modifier.padding(paddingValues)) {
//                groupedAttendance.forEach { (date, records) ->
//                    // Display date header
//                    item {
//                        Text(
//                            text = date,
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            color = MaterialTheme.colorScheme.primary,
//
//                            )
//
//                    }
//                    // Display list of attendance records for the date
//                    items(records) { record ->
//                        AttendanceCard(
//                            studentName = record["studentName"] ?: "",
//                            classGrade = record["classGrade"] ?: ""
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun AttendanceCard(studentName: String, classGrade: String) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(
//                text = "Student Name: $studentName",
//                style = MaterialTheme.typography.bodySmall,
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Class Grade: $classGrade",
//                style = MaterialTheme.typography.bodySmall,
//                fontSize = 16.sp
//            )
//        }
//    }
//}

