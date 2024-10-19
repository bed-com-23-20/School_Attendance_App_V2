package com.example.school_attendance_register

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun EnrollStudent(
    onEnroll: (String, String, String, String, String, String, String) -> Unit, // Callback for enrollment
    onBack: () -> Unit // Callback for navigating back
) {
    // State variables for inputs
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) } // Guardian Name state
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) } // Guardian Phone state
    var className by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf("") } // Date of Birth state
    var gender by remember { mutableStateOf("") } // State for gender selection

    var showError by remember { mutableStateOf(false) } // State for showing error message

    // Variables to manage date picker dialog
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // DatePickerDialog to pick date
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            // Update the dateOfBirth state with the selected date
            dateOfBirth = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )

    // Main Column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header for "Enroll Student"
        Text(
            text = "STUDENT ENROLLMENT PAGE", // Adjusted heading text
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )
        Divider(
            thickness = 1.dp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // A Column layout for input fields
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start // Align elements to the start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Student first name input
            OutlinedTextField(
                value = fname,
                onValueChange = { fname = it },
                label = { Text("Student Firstname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Student surname input
            OutlinedTextField(
                value = sname,
                onValueChange = { sname = it },
                label = { Text("Student Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Guardian name input
            OutlinedTextField(
                value = guardianName,
                onValueChange = { guardianName = it },
                label = { Text("Guardian Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Guardian phone number input
            OutlinedTextField(
                value = guardianPhone,
                onValueChange = { guardianPhone = it },
                label = { Text("Guardian Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Class input
            OutlinedTextField(
                value = className,
                onValueChange = { className = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date of Birth picker
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { /* No input here, handled by DatePickerDialog */ },
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datePickerDialog.show() // Show date picker dialog when clicked
                    },
                enabled = false // Disable manual input, allow only date picker
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Gender label
            Text(text = "Gender", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            // Gender selection row with text and radio buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Male")
                    RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Female")
                    RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Other")
                    RadioButton(selected = gender == "Other", onClick = { gender = "Other" })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show error message if form is incomplete
            if (showError) {
                Text(
                    text = "Please fill all the necessary details before submitting.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enroll Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    // Validation check
                    if (fname.text.isBlank() || sname.text.isBlank() || guardianName.text.isBlank() ||
                        guardianPhone.text.isBlank() || className.text.isBlank() ||
                        dateOfBirth.isBlank() || gender.isBlank()
                    ) {
                        // If any field is empty, show error message
                        showError = true
                    } else {
                        // All fields are filled, proceed with enrollment
                        showError = false
                        onEnroll(
                            fname.text,
                            sname.text,
                            guardianName.text,
                            guardianPhone.text,
                            className.text,
                            dateOfBirth,
                            gender
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Enroll")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Back Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Back")
            }
        }
    }
}

// Preview function for the EnrollStudent composable
@Preview(showBackground = true)
@Composable
fun PreviewEnrollStudent() {
    EnrollStudent(
        onEnroll = { fname, sname, guardianName, guardianPhone, className, dateOfBirth, gender -> },
        onBack = {}
    )
}
