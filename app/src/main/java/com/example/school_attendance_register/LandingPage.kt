package com.example.school_attendance_register

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)

fun LandingPage(navController: NavController){

    // Get current time
    val currentTime = LocalTime.now()

    // Define a DateTimeFormatter for AM/PM
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")

    // Format the time to display it in AM/PM format
    val formattedTime = currentTime.format(formatter)

    // Check if the time is AM or PM
    if (formattedTime.contains("AM")) {
        println("It's AM!")
    }
    else {
        println("It's PM!")
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.school), contentDescription = "Landing page Image",
        modifier = Modifier.size(200.dp))

        Column(
            //modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = "Welcome to", fontSize = 28.sp, fontWeight =  FontWeight.SemiBold)

        }
        Text(text = "Attendance Register System", fontSize = 28.sp, fontWeight =  FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))


            if (formattedTime.contains("AM")) {
                Text(text = "What are you up to this morning?", fontSize = 20.sp,)
            }

            else{
                Text(text = "What are you up to this afternoon?", fontSize = 20.sp)
            }
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate("Login_Page")
        }) {
            Text(text = "Register Student     >>")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(onClick = {}) {
            Text(text = "Mark Attendance      >>",

            )
        }



    }
}