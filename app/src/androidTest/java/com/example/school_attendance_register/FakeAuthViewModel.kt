package com.example.school_attendance_register.plastol_pages

import androidx.lifecycle.ViewModel

class FakeAuthViewModel : ViewModel() {
    fun fetchUserCredentials(email: String, callback: (Result<Pair<String, String>>) -> Unit) {
        // Simulate success response
        callback(Result.success(Pair(email, "password123")))
    }
}


