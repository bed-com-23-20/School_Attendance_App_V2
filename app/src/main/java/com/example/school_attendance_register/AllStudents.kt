package com.example.school_attendance_register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AllStudents(result: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "ENROLLED STUDENTS",
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .background(color = Color.White)

        )
    }

    Spacer(modifier = Modifier.height(20.dp))


    val students = result.split("------------------------------------------------------------------------")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 13.dp, top = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {


        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(students) { student ->
                Text(
                    text = student,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Divider(color = Color.Gray)
            }
        }
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//    Text(
//        text = "Enrolled Students",
//        fontSize = 28.sp,
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.Bold,
//    )
//    Divider(
//        thickness = 1.dp,
//        color = Color.Black,
//        modifier = Modifier.padding(horizontal = 16.dp)
//    )
//
//        Text(text = result, fontSize = 16.sp, color = Color.Black)
//    }
}

