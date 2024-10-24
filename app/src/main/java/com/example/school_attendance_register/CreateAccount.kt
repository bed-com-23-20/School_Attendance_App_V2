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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale


@SuppressLint("SuspiciousIndentation")
@Composable
@Preview(showBackground = true)
fun CreateAccount(navController: NavController){

    //Database Connection
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")

    var loading by remember { mutableStateOf(false) }

    // Admin Information
    var adminFullName by remember { mutableStateOf("") }
    var adminSname by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var createPass by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //Validation checks
    var result by remember { mutableStateOf("") }
    var check by remember { mutableStateOf<Boolean>(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally

    ){
        Text(
            text = "Create Account",
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

        TextField(
            value = adminFullName ,
            onValueChange = {adminFullName  = it},
            label = {Text("Full Name")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)


        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = schoolName,
            onValueChange = {schoolName = it},
            label = {Text("School Name")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)


        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = district,
            onValueChange = {district = it},
            label = {Text("District ")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)


        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = {Text("Phone Number")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)

        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = createPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { createPass = it },
            label = { Text("Create Password") },
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, end = 20.dp
                )
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            label = { Text("Confirm Password") },
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, end = 20.dp
                )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 30.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = {
                navController.navigate("Login_Page")
            },

                colors = ButtonDefaults.buttonColors(containerColor  = Color.Black)

            ) {
                Text(
                    text = "Cancel",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif


                )
            }
            Spacer(modifier = Modifier.width(50.dp))

            Button(
                onClick = {
                    loading = true

              if(createPass != password && adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty() &&
                  phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(context, "Password does not match. Re-check the password", Toast.LENGTH_LONG).show()

              }

          else if(adminFullName.isNotEmpty() && schoolName.isNotEmpty() && district.isNotEmpty() &&
                    phoneNumber.isNotEmpty() && email.isNotEmpty() && createPass.isNotEmpty() && password.isNotEmpty() &&
                    createPass == password
                    ){
                  //var adminInfo = AdminInfo(adminFullName, schoolName, district, phoneNumber.toInt(), email, createPass, confirmPass)
                    val adminInfo = AdminInfo(adminFullName.toUpperCase(Locale.ROOT),schoolName, district, phoneNumber.toInt(), email, createPass, password)
                  myRef.child(adminFullName).setValue(adminInfo).addOnSuccessListener {
                    adminFullName = ""
                    schoolName = ""
                    district = ""
                    phoneNumber = ""
                    email = ""
                    createPass =""
                      password = ""
                        Toast.makeText(context, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show()
                         navController.navigate("Admin_Dash_Board")  
                    }.addOnFailureListener{
                        Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                    }

                }
                else{
                    Toast.makeText(context, "Please insert all the values first before submitting", Toast.LENGTH_SHORT).show()
                }

            },

                colors = ButtonDefaults.buttonColors(containerColor  = Color.Black)

            ) {
                Text(
                     "Submit",
                    //text = "Submit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif


                )
            }



        }

    }


}