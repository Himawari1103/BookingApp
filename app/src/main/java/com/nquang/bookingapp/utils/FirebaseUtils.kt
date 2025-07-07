package com.nquang.bookingapp.utils

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
                        "FindUserByEmail Debug",
                        "Found user with UID: $uid, email: $email, fullName: $fullName"
                    )
                    UserModel(fullName = fullName, email = email, uid = uid)
                }
            } catch (e: Exception) {
                Log.e("FindUserByEmail Error", "Error finding user by email: ${e.message}")
                null
            }
        }

        suspend fun findUserByUid(uid: String): UserModel? {
            return try {
                val userRef = database.reference.child("users").child(uid)
                var user: UserModel? = null
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        user = snapshot.getValue(UserModel::class.java)
                        if (user != null) {
                            println("Email: ${user!!.email}")
                            println("Full name: ${user!!.fullName}")
                        } else {
                            println("User not found!")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        println("Database error: ${error.message}")
                    }
                })

                Log.d("FindUserByUid Debug", "Found user: $user")
                user
            } catch (e: Exception) {
                Log.e("FindUserByUid Error", "Error finding user by uid: ${e.message}")
                null
            }
        }

        fun saveUserdata(uid: String, fullName: String, email: String) {
            //auth.currentUser!!.uid
            val user = UserModel(fullName = fullName, email = email, uid = uid)
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