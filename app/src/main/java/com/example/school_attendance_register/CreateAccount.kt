package com.example.school_attendance_register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
@Preview(showBackground = true)
fun CreateAccount(navController: NavController){

    var adminFname by remember { mutableStateOf("") }
    var adminSname by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
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
            value = adminFname,
            onValueChange = {adminFname = it},
            label = {Text("First Name")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)

        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = adminSname,
            onValueChange = {adminSname = it},
            label = {Text("Surname")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)

        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = schoolName,
            onValueChange = {schoolName = it},
            label = {Text("School Name")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)

        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = district,
            onValueChange = {district = it},
            label = {Text("District ")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)

        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = {Text("Phone Number")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            //leadingIcon = {ImageVector.vectorResource(id = R.drawable.password_vector)},
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(24.dp)
//                ),
//            shape = RoundedCornerShape(24.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(start = 40.dp, end = 30.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = {
                navController.navigate("Login_Page")
            },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp),
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

            Button(onClick = {
                navController.navigate("Confirm_Password_page")
            },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor  = Color.Black)

            ) {
                Text(
                    text = "  Next  ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif


                )
            }

        }

    }


}