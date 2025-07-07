package com.nquang.bookingapp.login.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nquang.bookingapp.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: UserModel) : AuthState()
    data class Error(val message: String) : AuthState()

    val isLoading: Boolean
        get() = this is Loading
    val errorMessage: String?
        get() = (this as? Error)?.message
    val userEmail: String?
        get() = (this as? Success)?.user.takeIf { it?.email != null }?.email
}

class RegisterViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    init {
        database.setPersistenceEnabled(false)
    }

    fun updateFullName(fullName: String) {
        this.fullName = fullName.trim()
    }

    fun updateEmail(email: String) {
        this.email = email.trim()
    }

    fun updatePassword(password: String) {
        this.password = password.trim()
    }

    fun updateConfirmPassword(confirmPassword: String) {
        this.confirmPassword = confirmPassword.trim()
    }

    suspend fun register(): Boolean {
        if (password != confirmPassword) return false;
        return try {
            _authState.value = AuthState.Loading
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                val user = UserModel(fullName, email, password)
                saveUserdata(auth.currentUser!!.uid,user.fullName!!, user.email!!)
                _authState.value = AuthState.Success(user)
                true
            } else {
                _authState.value = AuthState.Error("Registration failed: No user found")
                false
            }
        } catch (e: Exception) {
            Log.d("RegisterViewModel", "Registration failed", e)
            _authState.value = AuthState.Error("Registration failed: ${e.message}")
            false
        }
    }

    private fun saveUserdata(uid: String, fullName: String, email: String) {
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