package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StaffRecords(navController: NavController) {
    // Temporary static list of staff
    val staffList = listOf(
        StaffInfo(name = "Ibula", position = "Teacher", email = "ibula@example.com", phone = "123-456-7890", staffId = "S001"),
        StaffInfo(name = "James", position = "Principal", email = "james@example.com", phone = "234-567-8901", staffId = "S002"),
        StaffInfo(name = "Alice", position = "Tutor", email = "alice@example.com", phone = "345-678-9012", staffId = "S003")
    )

    var searchQuery by remember { mutableStateOf("") }
    val filteredList = staffList.filter {
        it.name.contains(searchQuery, ignoreCase = true) || it.staffId.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController,
                title = "Staff Records",
                backButtonColor = Color.Red,
                backIconColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Name or ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (filteredList.isEmpty()) {
                Text(
                    text = "No records found.",
                    color = Color.Gray,
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    filteredList.forEach { staff ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Name: ${staff.name}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Position: ${staff.position}", fontSize = 14.sp)
                                Text(text = "Email: ${staff.email}", fontSize = 14.sp)
                                Text(text = "Phone: ${staff.phone}", fontSize = 14.sp)
                                Text(text = "Staff ID: ${staff.staffId}", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("Home_Page") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Back", fontSize = 18.sp, color = Color.White)
                }

                Button(
                    onClick = { /* Add Staff Logic */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Add Staff", fontSize = 18.sp, color = Color.White)
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
