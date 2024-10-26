package com.example.school_attendance_register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState
import com.google.firebase.database.*

class AuthViewModel : ViewModel() {


//    private val _authState = MutableLiveData<AuthState>()
//    val authState: LiveData<AuthState> = _authState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Admin")


//    init {
//        checkAuthStatus()
//    }
//
//
//    fun checkAuthStatus(){
//        if(auth.currentUser==null){
//            _authState.value = AuthState.Unauthenticated
//        }else{
//            _authState.value = AuthState.Authenticated
//        }
//    }



    // Function to fetch credentials from Firebase Realtime Database
    fun fetchCredentials(
        onComplete: (String, String) -> Unit,
        onError: (String) -> Unit
    ) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (adminSnapshot in snapshot.children) {
                    val email = snapshot.child("email").value as? String ?: ""
                    val password = snapshot.child("createPass").value as? String ?: ""

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        onComplete(
                            email,
                            password
                        )  // Pass email and password to the onComplete lambda
                        return
                    }
                }
                onError("Email or password not found in the database")

            }

            override fun onCancelled(error: DatabaseError) {
                onError("Error fetching data: ${error.message}")  // Pass error message to the onError lambda
            }
        })
    }

    // Function to log in using Firebase Authentication
    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,     // Lambda for handling login success
        onFailure: (String) -> Unit  // Lambda for handling login failure
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()  // Call onSuccess if login succeeds
                } else {
                    task.exception?.message?.let { onFailure(it) }  // Pass the error message to onFailure if login fails
                }
            }
    }

}

