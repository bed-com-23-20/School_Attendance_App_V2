package com.example.school_attendance_register
import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("SuspiciousIndentation")
@Composable
fun MarkAttendance(navController: NavController) {
    // State to hold the student code input
    var studentCode by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    //Firebase instances
    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = " MARK ATTENDANCE",
                backButtonColor = Color.Red,
                backIconColor = Color.White
                )
        }
    ) {paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
//            Text(
//                text = "MARK ATTENDANCE",
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily.Serif
//            )
//
//            Spacer(modifier = Modifier.height(40.dp))

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
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

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
                            Toast.makeText(context, "The Code Can not be Empty", Toast.LENGTH_LONG)
                                .show()
                        } else {

                            myRefStudent.get()
                                .addOnSuccessListener { snapshot ->
                                    var studentName: String? = null

                                    for (child in snapshot.children) {
                                        val codeId =
                                            child.child("uniqueId").getValue(String::class.java)
                                        if (codeId == studentCode) {
                                            studentName =
                                                child.child("fullName").getValue(String::class.java)
                                            break
                                        }
                                    }
                                    if (studentName != null) {
                                        Toast.makeText(
                                            context,
                                            "Code exists! Student Name: $studentName",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        message =
                                            "$studentName has successfully marked present today"
                                        studentCode = ""
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Code you entered does not exist!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        message = ""
                                    }
                                }.addOnFailureListener { exception ->
                                    Toast.makeText(
                                        context,
                                        "Error checking code: ${exception.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                        }


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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBack(
    navController: NavController, title: String,
    backButtonColor: Color = MaterialTheme.colorScheme.primary, // Default color
    backIconColor: Color = Color.White
                      ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,

            )
        },
        navigationIcon = {
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .background(backButtonColor, shape = MaterialTheme.shapes.small)
//                    .clickable { navController.popBackStack() }, // Back navigation
//                contentAlignment = Alignment.Center
//            ) {
            IconButton(onClick = {
                navController.popBackStack() // Navigates back to the previous screen
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )

            }
        },

    )

}
