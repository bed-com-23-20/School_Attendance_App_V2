package com.example.school_attendance_register.owen_pages
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

@Composable
fun MarkAttendance(navController: NavController) {
    // State to hold the student code input
    var studentCode by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    //Firebase instances
    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "MARK ATTENDANCE",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Student Code Input Field
        OutlinedTextField(
            value = studentCode,
            onValueChange = { studentCode = it },
            label = { Text(text = "Enter student code") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Display feedback message if attendance is marked
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = androidx.compose.ui.graphics.Color.Green,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = { navController.navigate("Landing_Page") },
                modifier = Modifier.padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ) {
                Text(
                    text = "Cancel",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    if (studentCode.isEmpty()) {
                        Toast.makeText(context,"The Code Can not be Empty", Toast.LENGTH_LONG).show()
                    }
                    else{

                    }







                    // Logic to mark attendance
//                    if (studentCode.isNotBlank()) {
//                        message = "Attendance marked for student code: $studentCode"
//                        studentCode = "" // Reset input after submission
//                    } else {
//                        message = "Please enter a valid student code."
//                    }

                },
                modifier = Modifier.padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            ) {
                Text(
                    text = "Submit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

            }
        }
    }
}
