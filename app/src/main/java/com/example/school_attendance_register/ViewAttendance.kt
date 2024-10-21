package com.example.school_attendance_register

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ViewAttendance(
    onBack: () -> Unit // Callback to navigate back
) {
    var selectedTerm by remember { mutableStateOf("First Term") }
    var startDate by remember { mutableStateOf("") }
    var attendanceData by remember { mutableStateOf<List<AttendanceRecord>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(text = "View Attendance", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Term selection
        DropdownMenu(
            selectedItem = selectedTerm,
            onSelectedItemChange = { selectedTerm = it },
            items = listOf("First Term", "Second Term", "Third Term")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Start date input
        Text(text = "Modify Start Date")
        BasicTextField(
            value = startDate,
            onValueChange = { startDate = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Attendance Records Display
        Text(text = "Attendance Records:", fontSize = 22.sp)
        // This is a placeholder for displaying the attendance records
        LazyColumn {
            items(attendanceData) { record ->
                AttendanceRow(record) // A composable to display individual records
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back Button
        Button(onClick = onBack) {

            Text(text = "Back")
        }
    }
}

@Composable
fun AttendanceRow(record: AttendanceRecord) {
    // Display individual attendance record details
    Text(text = "${record.studentName} - ${record.status}")
}

data class AttendanceRecord(val studentName: String, val status: String) // Sample data class for attendance records

@Composable
fun DropdownMenu(
    selectedItem: String,
    onSelectedItemChange: (String) -> Unit,
    items: List<String>
) {
    // Basic Dropdown Menu implementation
    // Add your own DropdownMenu logic here
}

@Preview(showBackground = true)
@Composable
fun PreviewViewAttendanceScreen() {
    ViewAttendance(onBack = {})
}