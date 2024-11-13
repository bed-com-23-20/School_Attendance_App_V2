
package com.example.school_attendance_register.plastol_pages

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.mindrot.jbcrypt.BCrypt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.school_attendance_register.chikondi_pages.EnrollStudent
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
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
                // Fetch the user data from the Admin node using the encoded email
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


