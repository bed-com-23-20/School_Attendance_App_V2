package com.example.school_attendance_register.chikondi_pages

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
import com.google.firebase.database.FirebaseDatabase
import java.util.*

@Composable
fun EnrollStudent(navController: NavController) {
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) }
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) }
    var classform by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, selectedYear, selectedMonth, selectedDay ->
            dateOfBirth = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "STUDENT ENROLLMENT PAGE",
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = fname,
                onValueChange = { fname = it },
                label = { Text("Student Firstname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = sname,
                onValueChange = { sname = it },
                label = { Text("Student Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = guardianName,
                onValueChange = { guardianName = it },
                label = { Text("Guardian Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = guardianPhone,
                onValueChange = { guardianPhone = it },
                label = { Text("Guardian Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = classform,
                onValueChange = { classform = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { },
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() },
                enabled = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Gender")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
                    Text(text = "Male")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
                    Text(text = "Female")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = gender == "Other", onClick = { gender = "Other" })
                    Text(text = "Other")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showError) {
                Text(
                    text = "Please fill all the necessary details before submitting.",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
//                    if(fname.isNotEmpty() && sname.isNotEmpty() && guardianName.isNotEmpty() && guardianPhone.isNotEmpty() &&
//                        classform.isNotEmpty() && dateOfBirth.isNotEmpty() && gender.isNotEmpty()
//                        ){
//                        var studentInfo = StudentInfo(
//                            fname.toString(), sname.toString(), guardianName.toString(),
//                            guardianPhone, classform, dateOfBirth, gender
//                        )
//                        myRef.child(adminFullName).setValue(adminInfo).addOnSuccessListener
//
//                    }
                    

                },


                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("Admin_Dash_Board")
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}