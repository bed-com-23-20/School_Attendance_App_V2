package com.example.school_attendance_register.data_classes


data class AdminInfo(val adminFullName : String,
                     val schoolName : String,
                     val district: String,
                     val phoneNumber : Int,
                     val email : String,
                     val createPass : String,
                     val confirmPass : String) {

}


