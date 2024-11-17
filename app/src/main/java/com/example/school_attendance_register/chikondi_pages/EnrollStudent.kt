package com.example.school_attendance_register.chikondi_pages

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import com.example.school_attendance_register.plastol_pages.AuthViewModel
import com.example.school_attendance_register.plastol_pages.data_classes.AdminInfo
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
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
    val myRefStudent = database.getReference("Students") //.child(encodeEmail).child("Students")
    val context = LocalContext.current

    var result by remember { mutableStateOf("") }
    var check by remember { mutableStateOf<Boolean>(false) }

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                    firstNameChar = if (fname.text.isNotEmpty()) fname.text[0].toUpperCase() else null
                },
                label = { Text("Student Firstname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = sname,
                onValueChange = {
                    sname = it
                    surNameChar = if (sname.text.isNotEmpty()) sname.text[0].toUpperCase() else null
                },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = classform,
                onValueChange = { classform = it },
                label = { Text("Class") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {

                        try {
                            val randomNumber = Random.nextInt(100000, 1000000)
                            val uniqueId = "${firstNameChar?.toUpperCase()}${surNameChar?.toUpperCase()}-$randomNumber-${classform.text}"

                            if (fname.text.isNotEmpty() && sname.text.isNotEmpty() && guardianName.text.isNotEmpty() && dateOfBirth.isNotEmpty() &&
                                guardianPhone.text.isNotEmpty() && classform.text.isNotEmpty() &&
                                gender.isNotEmpty()
                            ) {

                                val sanitizedFName = fname.text.replace("[./#$\\[\\]]".toRegex(), "")
                                val sanitizedSName = sname.text.replace("[./#$\\[\\]]".toRegex(), "")
                                val sanitizedGuardianName = guardianName.text.replace("[./#$\\[\\]]".toRegex(), "")
                                val sanitizedGuardianPhone = guardianPhone.text.replace("[./#$\\[\\]]".toRegex(), "")
                                val sanitizedGuardDateOfBirth = dateOfBirth.replace("[./#$\\[\\]]".toRegex(), "")
                                val sanitizedClassForm = classform.text.replace("[./#$\\[\\]]".toRegex(), "")

                                val sanitizedFullName = "$sanitizedFName $sanitizedSName".toUpperCase(Locale.ROOT)

                                // Create the StudentInfo object with sanitized fields
                                val studentInfo = StudentInfo(
                                    sanitizedFName.toUpperCase(Locale.ROOT),
                                    sanitizedSName.toUpperCase(Locale.ROOT),
                                    sanitizedGuardianName,
                                    sanitizedGuardianPhone,
                                    sanitizedClassForm,
                                    sanitizedGuardDateOfBirth,
                                    gender,
                                    uniqueId
                                )
                                myRefStudent.child(sanitizedFullName).setValue(studentInfo)
                                    .addOnSuccessListener {
                                        fname = TextFieldValue("")
                                        sname = TextFieldValue("")
                                        guardianName = TextFieldValue("")
                                        guardianPhone = TextFieldValue("")
                                        classform = TextFieldValue("")
                                        dateOfBirth = ""
                                        gender = ""
                                        idTextF = "The ID Number is $uniqueId"

                                        Toast.makeText(
                                            context,
                                            "Student Successfully Enrolled",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        //navController.navigate("Admin_Dash_Board")
                                        Log.d("Successful", "Student info saved successfully")
                                        //Displaying the Students Data

                                        val data = StringBuffer()
                                        myRefStudent.get().addOnSuccessListener { it1 ->
                                            if(it1.exists()){
                                                it1.children.forEach{
                                                    data.append("\nFirst Name = "+it.child("fname").value)
                                                    data.append("\nSurname  = "+it.child("sname").value)
                                                    data.append("\nUnique Code  = "+it.child("uniqueId").value)
                                                    data.append("\nGuardian Name = "+it.child("guardianName").value)
                                                    data.append("\nGuardian Contact = "+it.child("guardianPhone").value)
                                                    data.append("\nClass = "+it.child("classform").value)
                                                    data.append("\nDate of Birth = "+it.child("dateOfBirth").value)
                                                    data.append("\nGender = "+it.child("gender").value)
                                                    data.append("\n------------------------------------------------------------------------")
                                                }
                                                check = true
                                                result = data.toString()

                                                navController.navigate("allStudents/$result")
                                            }

                                        }.addOnFailureListener{
                                            Toast.makeText(context, "No Students found", Toast.LENGTH_SHORT).show()
                                        }


                                    }.addOnFailureListener { e ->
                                        Toast.makeText(
                                            context,
                                            "Failed to save student info: ${e.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        Log.d("FirebaseError", "Error saving student info: ${e.message}")
                                    }

                            } else {
                                Toast.makeText(
                                    context,
                                    "Please insert all the values first before submitting",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("EmptyFields", "Fill the fields")
                                return@Button
                            }


                        }catch (e: Exception){
                            Log.d("FirebaseError", "Error saving student info: ${e.message}")
                        }



                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),


                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }


            }

//            AnimatedVisibility(visible = check, Modifier.fillMaxWidth())
//            {
//                Text(text = result, fontSize = 15.sp, color = Color.Black)
//            }
        }
    }
}
