package com.nquang.bookingapp.utils

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.nquang.bookingapp.model.UserModel
import kotlinx.coroutines.tasks.await

class FirebaseUtils {
    companion object {
        val database = FirebaseDatabase.getInstance()

        suspend fun findUserByEmail(email: String): UserModel? {
            return try {
                val query = database.reference.child("users")
                    .orderByChild("email")
                    .equalTo(email)
                    .get()
                    .await()
                query.children.firstOrNull()?.let { snapshot ->
                    val uid = snapshot.key ?: ""
                    val email = snapshot.child("email").getValue(String::class.java) ?: ""
                    val fullName = snapshot.child("fullName").getValue(String::class.java) ?: ""
                    Log.d(
                        "ForgotPasswordViewModel Debug",
                        "Found user with UID: $uid, email: $email, fullName: $fullName"
                    )
                    UserModel(fullName = fullName, email = email)
                }
            } catch (e: Exception) {
                Log.e("ForgotPasswordViewModel Debug", "Error finding user by email: ${e.message}")
                null
            }
        }

        fun saveUserdata(uid: String, fullName: String, email: String) {
            //auth.currentUser!!.uid
            val user = UserModel(fullName, email)
            //chèn dữ liệu vào database
            database.getReference().child("users").child(uid).setValue(user)
                .addOnSuccessListener {
                    Log.d("saveUserdata", "Success")
                }
                .addOnFailureListener { exception ->
                    Log.d("saveUserdata", "Failure", exception)
                }
        }

    }
}