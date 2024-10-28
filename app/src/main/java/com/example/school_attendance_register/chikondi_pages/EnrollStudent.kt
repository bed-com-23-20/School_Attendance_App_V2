package com.example.school_attendance_register.chikondi_pages

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.CreateAccount
import com.example.school_attendance_register.plastol_pages.data_classes.AdminInfo
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.random.Random

@Composable
fun EnrollStudent(navController: NavController, adminFullName: String) {
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) }
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) }
    var classform by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var uniqueId by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    var firstNameChar by remember { mutableStateOf<Char?>(null) }
    var surNameChar by remember { mutableStateOf<Char?>(null) }


    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin").child(adminFullName).child("Students")
    val context = LocalContext.current

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
//        Text(
//            text = "STUDENT ENROLLMENT PAGE",
//            fontSize = 28.sp,
//            fontFamily = FontFamily.Serif,
//            fontWeight = FontWeight.Bold
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = fname,
                onValueChange = {
                    fname = it
                    //firstNameChar = if (it.text.isNotEmpty()) it[0] else null
                                },
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

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uniqueId,
                onValueChange = {},
                label = { Text("Do not Write here") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            )

//            if (showError) {
//                Text(
//                    text = "Please fill all the necessary details before submitting.",
//                    color = MaterialTheme.colorScheme.error
//                )
//            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        val randomNumber = Random.nextInt(100000, 1000000)



                        if (fname.text.isNotEmpty() && sname.text.isNotEmpty() && guardianName.text.isNotEmpty() &&
                            guardianPhone.text.isNotEmpty() && classform.text.isNotEmpty() && dateOfBirth.isNotEmpty() &&
                            gender.isNotEmpty()
                        ) {
                            //val firstNameChar = fname[0]
                            val fullName: String = ("$fname $sname")
                            val studentInfo = StudentInfo(
                                fname.text,
                                sname.text,
                                guardianName.text,
                                guardianPhone,
                                classform,
                                dateOfBirth,
                                gender,
                                uniqueId = ""
                            )
                            myRef.child(fullName).push().setValue(studentInfo)
                                .addOnSuccessListener {
                                    fname = TextFieldValue("")
                                    sname = TextFieldValue("")
                                    guardianName = TextFieldValue("")
                                    guardianPhone = TextFieldValue("")
                                    classform = TextFieldValue("")

                                    Toast.makeText(
                                        context,
                                        "Student Successfully Enrolled",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }.addOnFailureListener { e ->
                                Toast.makeText(
                                    context,
                                    "Failed to save student info: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        } else {
                            Toast.makeText(
                                context,
                                "Please insert all the values first before submitting",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),


                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }

                Spacer(modifier = Modifier.width(6.dp))

                Button(
                    onClick = {
                        navController.navigate("Admin_Dash_Board")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            }
            ///////////////////////
        }
    }
}