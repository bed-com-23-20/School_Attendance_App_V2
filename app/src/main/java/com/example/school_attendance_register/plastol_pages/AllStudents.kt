package com.example.school_attendance_register.plastol_pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.school_attendance_register.plastol_pages.data_classes.StudentInfo
import com.google.firebase.database.FirebaseDatabase

@Composable
fun AllStudents(){
    
    val result by remember { mutableStateOf("") }
    val check by remember { mutableStateOf<Boolean>(false) }
    
//    val database = FirebaseDatabase.getInstance()
//    val enrolledSt = database.getReference("Students")
//
//
//    //var studentInfo = StudentInfo(fname, sname, guardianName, guardianPhone, classform, dateOfBirth, gender, uniqueId,)
//
//
//        AnimatedVisibility(visible = check, Modifier.fillMaxWidth())
//        {
//            Text(text = result, fontSize = 15.sp, color = Color.Black)
//        }

}

