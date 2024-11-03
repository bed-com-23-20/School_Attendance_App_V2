
package com.example.school_attendance_register.plastol_pages

import android.content.ContentValues.TAG
import android.util.Log
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



//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//    //private val uiHelper = UIHelper()
//
//
//    fun hashPassword(password: String): String {
//        return BCrypt.hashpw(password, BCrypt.gensalt())
//    }
//
//    fun loginUser(
//        userId: String,
//        providedPassword: String,
//        navyController: NavController,
//        onError: (String) -> Unit,
//        onSuccess: () -> Unit,
//    ) {
//
//        val usersRef = database.getReference("Admin").child(userId)
//
//        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val email = snapshot.child("email").getValue(String::class.java)
//                val password= snapshot.child("confirmPass").getValue(String::class.java)
//
//                if (email != null && password != null) {
//                    val hashedPassword = hashPassword(providedPassword)
//
//                    if (hashedPassword == password) {
//                        auth.signInWithEmailAndPassword(email, providedPassword)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful) {
//                                    Log.d(TAG, "signInWithEmail:success")
//                                    Log.d(TAG,"Email $email\nPassword $password")
//                                    onSuccess.invoke()
//                                    navyController.navigate("Admin_Dash_Board")
//                                } else {
//                                    val errorMessage =
//                                        task.exception?.message ?: "Authentication failed"
//                                    onError(errorMessage) // Call onError lambda with error message
//                                }
//                            }.addOnFailureListener{ error:Exception ->
//                                Log.e("onError", "Error: ${error.localizedMessage ?: "Unknown error"}")
//                            }
//                    } else {
//                        onError("Incorrect password")
//                    }
//                } else {
//                    onError("User not found")
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException())
//                onError("Database error: ${error.message}")
//            }
//        })
//    }

    /////////

//}

