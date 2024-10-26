// StaffViewModel.kt
package com.example.school_attendance_register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StaffViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registerStaff(
        name: String,
        email: String,
        phone: String,
        className: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        // Validate input data
        if (!isValidData(name, email, phone, className, password)) {
            onResult(false, "Please enter all fields correctly.")
            return
        }

        // Firebase Authentication for creating a new user
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val staffData = mapOf(
                        "name" to name,
                        "email" to email,
                        "phone" to phone,
                        "className" to className,
                        "uid" to userId
                    )

                    // Save additional staff data in Firestore
                    db.collection("staff").document(userId).set(staffData)
                        .addOnSuccessListener {
                            onResult(true, "Registration successful!")
                        }
                        .addOnFailureListener { e ->
                            onResult(false, "Firestore error: ${e.message}")
                        }
                } else {
                    onResult(false, "Authentication failed: ${task.exception?.message}")
                }
            }
    }

    // Helper function for validating input data
    private fun isValidData(
        name: String,
        email: String,
        phone: String,
        className: String,
        password: String
    ): Boolean {
        return name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() &&
                className.isNotBlank() && password.length >= 6 &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
