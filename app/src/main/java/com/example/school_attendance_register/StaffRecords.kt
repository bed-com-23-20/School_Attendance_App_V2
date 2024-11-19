package com.example.school_attendance_register

import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StaffRecords(navController: NavController) {
    // Temporary static list of staff
    val staffList = listOf(
        StaffInfo(name = "John Doe", position = "Teacher", email = "john.doe@example.com", phone = "123-456-7890", staffId = "S001"),
        StaffInfo(name = "Jane Smith", position = "Principal", email = "jane.smith@example.com", phone = "234-567-8901", staffId = "S002"),
        StaffInfo(name = "Alice Brown", position = "Counselor", email = "alice.brown@example.com", phone = "345-678-9012", staffId = "S003")
    )

    // State for search query
    var searchQuery by remember { mutableStateOf("") }

    // Filtering the staff list based on search query
    val filteredList = staffList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.staffId.contains(searchQuery, ignoreCase = true)
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
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Name or ID") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredList.isEmpty()) {
                Text(text = "No records found.", color = Color.Gray)
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    filteredList.forEach { staff ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Name: ${staff.name}")
                                Text(text = "Position: ${staff.position}")
                                Text(text = "Email: ${staff.email}")
                                Text(text = "Phone: ${staff.phone}")
                                Text(text = "Staff ID: ${staff.staffId}")
                            }
                        }
                    }
                }
            }
        }
    }
}

data class StaffInfo(
    val name: String,
    val position: String,
    val email: String,
    val phone: String,
    val staffId: String
)

@Preview(showBackground = true)
@Composable
fun PreviewStaffRecords() {
    StaffRecords(navController = NavController(LocalContext.current)) // Mock NavController
}
