package com.example.school_attendance_register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

sealed class AuthState {
    object Authenticated : AuthState()
    data class Error(val message: String) : AuthState()
    object Unauthenticated : AuthState()
}

class AuthViewModel : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Admin")

    init {
        checkAuthStatus()
    }

    // Check if user is authenticated on init
    private fun checkAuthStatus() {
        _authState.value = if (auth.currentUser == null) {
            AuthState.Unauthenticated
        } else {
            AuthState.Authenticated
        }
    }

    // Fetch credentials from Firebase Realtime Database
    fun fetchCredentials() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var credentialsFound = false

                for (adminSnapshot in snapshot.children) {
                    val email = adminSnapshot.child("email").value as? String ?: ""
                    val password = adminSnapshot.child("createPass").value as? String ?: ""

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        // Proceed with login using found credentials
                        login(email, password)
                        credentialsFound = true
                        break
                    }
                }
                if (!credentialsFound) {
                    _authState.value = AuthState.Error("Email or password not found in the database")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                _authState.value = AuthState.Error("Error fetching data: ${error.message}")
            }
        })
    }

    // Log in using Firebase Authentication
    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Authentication failed")
                }
            }
    }

    // Log out function
    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}
