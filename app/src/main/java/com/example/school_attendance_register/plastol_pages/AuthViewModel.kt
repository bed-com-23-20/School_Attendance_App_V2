
package com.example.school_attendance_register.plastol_pages

import android.content.ContentValues.TAG
import android.util.Log
import org.mindrot.jbcrypt.BCrypt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AuthViewModel : ViewModel() {


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //private val uiHelper = UIHelper()


    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun loginUser(
        userId: String,
        providedPassword: String,
        navyController: NavController,
        onError: (String) -> Unit,
        onSuccess: () -> Unit,
        //name: onSuccess
    ) {
        val usersRef = database.getReference("Admin").child(userId)

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val email = snapshot.child("email").getValue(String::class.java)
                val password= snapshot.child("confirmPass").getValue(String::class.java)

                if (email != null && password != null) {
                    val hashedPassword = hashPassword(providedPassword)

                    if (hashedPassword == password) {
                        auth.signInWithEmailAndPassword(email, providedPassword)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "signInWithEmail:success")
                                    Log.d(TAG,"Email $email\nPassword $password")
                                    onSuccess.invoke()
                                    navyController.navigate("Admin_Dash_Board")
                                } else {
                                    val errorMessage =
                                        task.exception?.message ?: "Authentication failed"
                                    onError(errorMessage) // Call onError lambda with error message
                                }
                            }
                    } else {
                        onError("Incorrect password")
                    }
                } else {
                    onError("User not found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
                onError("Database error: ${error.message}")
            }
        })
    }

}

