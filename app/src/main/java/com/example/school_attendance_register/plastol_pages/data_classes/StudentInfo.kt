package com.example.school_attendance_register.plastol_pages.data_classes

import androidx.compose.ui.text.input.TextFieldValue

data class StudentInfo(
    var fname: String,
    var sname: String,
    var guardianName: String,
    var guardianPhone: TextFieldValue,
    var classform: TextFieldValue,
    var dateOfBirth: String,
    var gender: String,
    val uniqueId: String,
    var fullName: String = fname +""+ sname

)
