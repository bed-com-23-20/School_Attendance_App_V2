
package com.example.school_attendance_register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel<DatabaseReference> : ViewModel() {
    private val database: com.google.firebase.database.DatabaseReference = Firebase.database.reference


    // Fetch user email and password from the Admin node
    fun fetchUserCredentials(email: String, onResult: (Result<Pair<String, String>>) -> Unit) {

        val encodedEmail = encodeEmail(email)

        viewModelScope.launch {
            try {

                val snapshot = database.child("Admin").child(encodedEmail).get().await()
                if (snapshot.exists()) {
                    // Fetch the password from the snapshot
                    val password = snapshot.child("confirmPass").getValue(String::class.java) ?: throw Exception("Password not found")
                    onResult(Result.success(Pair(email, password)))
                } else {
                    onResult(Result.failure(Exception("User not found")))
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error fetching user credentials: ${e.message}")
                onResult(Result.failure(e))
            }
        }
    }


    fun encodeEmail(email: String): String {
        return email.replace(".", ",")
    }
}

