package com.example.school_attendance_register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LandingPage(navController: NavController) {

    // Get and format current time
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val formattedTime = currentTime.format(formatter)

    // Determine greeting message based on AM/PM
    val greetingMessage = if (formattedTime.contains("AM")) {
        "What are you up to this morning?"
    } else {
        "What are you up to this afternoon?"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Landing Page Image
        Image(
            painter = painterResource(id = R.drawable.school),
            contentDescription = "Landing page Image",
            modifier = Modifier.size(200.dp)
        )

        // Welcome Texts
        Text(text = "Welcome to", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "Attendance Register System", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        // Dynamic greeting message based on time
        Text(text = greetingMessage, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        // Buttons
        Button(
            onClick = { navController.navigate("Login_Page") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Register Student",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { /* TODO: Add navigation action for marking attendance */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Mark Attendance",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { System.exit(0) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Exit",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}
