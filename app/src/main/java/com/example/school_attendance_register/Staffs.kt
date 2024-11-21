package com.example.school_attendance_register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.StaffInfo
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Staffs(navController: NavController) {
    var staffList by remember { mutableStateOf(listOf<StaffInfo>()) }

    // Fetch staff data from Firebase
    LaunchedEffect(Unit) {
        val database = FirebaseDatabase.getInstance()
        val myStaff = database.getReference("Staff")

        myStaff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val staffData = mutableListOf<StaffInfo>()
                snapshot.children.forEach { child ->
                    val staff = child.getValue(StaffInfo::class.java)
                    if (staff != null) {
                        staffData.add(staff)
                    }
                }
                staffList = staffData
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Failed to fetch staff: ${error.message}")
            }
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Staffs", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                //backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            staffList.forEach { staff ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            Log.d("StaffClick", "Clicked on ${staff.name}")
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Name: ${staff.name}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Email: ${staff.email}", fontSize = 16.sp)
                        Text("Phone: ${staff.phone}", fontSize = 16.sp)
                        Text("Class: ${staff.className}", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}


