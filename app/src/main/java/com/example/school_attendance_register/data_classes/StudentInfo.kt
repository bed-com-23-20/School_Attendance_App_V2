package com.example.school_attendance_register.data_classes

data class StudentInfo(
    var fname: String,
    var sname: String,
    var guardianName: String,
    var guardianPhone: String,
    var classform: String,
    var dateOfBirth: String,
    var gender: String,
    val uniqueId: String,
    var fullName: String = "$fname $sname"

)