package com.example.school_attendance_register.chikondi_pages

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
import com.example.school_attendance_register.ui.components.PageWithBackArrow
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.random.Random

@Composable
fun EnrollStudent(navController: NavController) {
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) }
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) }
    var classform by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var idTextF by remember { mutableStateOf("") }
    var firstNameChar by remember { mutableStateOf<Char?>(null) }
    var surNameChar by remember { mutableStateOf<Char?>(null) }

    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")
    val context = LocalContext.current
    var result by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, selectedYear, selectedMonth, selectedDay ->
            dateOfBirth = "$selectedDay -${selectedMonth + 1}-$selectedYear"
        },
        year, month, day
    )

    // Using PageWithBackArrow for consistent navigation
    PageWithBackArrow(navController = navController, title = "Enroll Student") { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Student Firstname
            OutlinedTextField(
                value = fname,
                onValueChange = {
                    fname = it
                    firstNameChar = if (fname.text.isNotEmpty()) fname.text[0].uppercaseChar() else null
                },
                label = { Text("Student Firstname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Student Surname
            OutlinedTextField(
                value = sname,
                onValueChange = {
                    sname = it
                    surNameChar = if (sname.text.isNotEmpty()) sname.text[0].uppercaseChar() else null
                },
                label = { Text("Student Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Guardian Name
            OutlinedTextField(
                value = guardianName,
                onValueChange = { guardianName = it },
                label = { Text("Guardian Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Guardian Phone Number
            OutlinedTextField(
                value = guardianPhone,
                onValueChange = { guardianPhone = it },
                label = { Text("Guardian Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Class Form
            OutlinedTextField(
                value = classform,
                onValueChange = { classform = it },
                label = { Text("Class") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date of Birth
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = {},
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() },
                enabled = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Gender")

            // Gender Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
                Text(text = "Male")
                RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
                Text(text = "Female")
                RadioButton(selected = gender == "Other", onClick = { gender = "Other" })
                Text(text = "Other")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Submit Button
            Button(
                onClick = {
                    val randomNumber = Random.nextInt(100000, 1000000)
                    val uniqueId = "${firstNameChar?.uppercaseChar()}${surNameChar?.uppercaseChar()}-$randomNumber-${classform.text}"

                    if (fname.text.isNotEmpty() && sname.text.isNotEmpty() && guardianName.text.isNotEmpty() &&
                        dateOfBirth.isNotEmpty() && guardianPhone.text.isNotEmpty() && classform.text.isNotEmpty() &&
                        gender.isNotEmpty()) {

                        val studentInfo = StudentInfo(
                            fname.text.uppercase(Locale.ROOT),
                            sname.text.uppercase(Locale.ROOT),
                            guardianName.text,
                            guardianPhone.text,
                            classform.text,
                            dateOfBirth,
                            gender,
                            uniqueId
                        )

                        myRefStudent.child("$uniqueId").setValue(studentInfo).addOnSuccessListener {
                            Toast.makeText(context, "Student Successfully Enrolled", Toast.LENGTH_SHORT).show()
                            navController.navigate("Admin_Dash_Board")
                        }.addOnFailureListener {
                            Toast.makeText(context, "Failed to enroll student: ${it.message}", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit", color = Color.White)
            }
        }
    }
}
