package com.nquang.bookingapp.login.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nquang.bookingapp.login.forgotpassword.ForgotPasswordEmailScreen
import com.nquang.bookingapp.model.UserModel
import com.nquang.bookingapp.utils.FirebaseUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class ForgotPasswordState {
    object Idle : ForgotPasswordState()
    object Loading : ForgotPasswordState()
    data class Success(val user: UserModel) : ForgotPasswordState()
    data class Error(val message: String) : ForgotPasswordState()

    val isLoading: Boolean
        get() = this is Loading
}

class ForgotPasswordViewModel: ViewModel() {
    var resetEmail by mutableStateOf("")

    private val _forgotPasswordState =
        MutableStateFlow<ForgotPasswordState>(ForgotPasswordState.Idle)
    val forgotPasswordState: StateFlow<ForgotPasswordState> = _forgotPasswordState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun updateResetEmail(email: String) {
        this.resetEmail = email.trim()
    }

    fun forgotPassword(context: Context) {
        if (resetEmail.isBlank()) {
            _forgotPasswordState.value = ForgotPasswordState.Error("Please enter your email")
            Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
            return
        }
        viewModelScope.launch {
            _forgotPasswordState.value = ForgotPasswordState.Loading
            try {
                val user = FirebaseUtils.findUserByEmail(resetEmail)
                if (user == null) {
                    _forgotPasswordState.value = ForgotPasswordState.Error("User not found")
                    Toast.makeText(context, "User not found $resetEmail", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                auth.sendPasswordResetEmail(resetEmail).await()
                _forgotPasswordState.value = ForgotPasswordState.Success(UserModel(email = resetEmail, uid = user.uid))
                Toast.makeText(context, "Password reset email sent", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                _forgotPasswordState.value = ForgotPasswordState.Error("Forgot password failed: ${e.message}")
                Toast.makeText(context, "Forgot password failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    suspend fun findUserByEmail(): UserModel? {
//        return try {
//            val database = FirebaseDatabase.getInstance()
//            val query = database.reference.child("users")
//                .orderByChild("email")
//                .equalTo(resetEmail)
//                .get()
//                .await()
//            query.children.firstOrNull()?.let { snapshot ->
//                val uid = snapshot.key ?: ""
//                val email = snapshot.child("email").getValue(String::class.java) ?: ""
//                val fullName = snapshot.child("fullName").getValue(String::class.java) ?: ""
//                Log.d("ForgotPasswordViewModel Debug", "Found user with UID: $uid, email: $email, fullName: $fullName")
//                UserModel(fullName = fullName,email = email)
//            }
//        } catch (e: Exception) {
//            Log.e("ForgotPasswordViewModel Debug", "Error finding user by email: ${e.message}")
//            null
//        }
//    }
}