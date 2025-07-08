package com.nquang.bookingapp.login.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nquang.bookingapp.utils.GoogleSignInUtils
import com.nquang.bookingapp.model.UserModel
import com.nquang.bookingapp.utils.FirebaseUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: UserModel) : LoginState()
    data class Error(val message: String) : LoginState()

    val isLoading: Boolean
        get() = this is Loading
}

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun updateEmail(email: String) {
        this.email = email.trim()
    }

    fun updatePassword(password: String) {
        this.password = password.trim()
    }

    // Login with Email/Password
    fun loginWithEmail(context: Context, login: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Please fill in all fields")
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val firebaseUser = result.user
                        if (firebaseUser != null) {
                            _loginState.value = LoginState.Success(
                                UserModel(
                                    email = firebaseUser.email,
                                    password = password,
                                    uid = firebaseUser.uid
                                )
                            )
                            login.invoke()
                        } else {
                            _loginState.value = LoginState.Error("Login failed: No user found")
                            Toast.makeText(
                                context,
                                "Login failed: No user found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    .addOnFailureListener { exception ->
                        _loginState.value = LoginState.Error("Login failed: ${exception.message}")
                        Toast.makeText(
                            context,
                            "Login failed: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Login failed: ${e.message}")
                Toast.makeText(context, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Login with Google
    fun loginWithGoogle(
        context: Context,
        scope: CoroutineScope,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
        login: () -> Unit
    ) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
//            val user = FirebaseAuth.getInstance().currentUser!!
//            val firebaseUser =
//                FirebaseUtils.findUserByEmail(user.email!!)

            try {
                GoogleSignInUtils.doGoogleSignIn(
                    context = context,
                    scope = scope,
                    launcher = launcher,
                    login = login,
//                    firebaseUser = firebaseUser
                )

            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Google login failed: ${e.message}")
                Toast.makeText(context, "Google login failed: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

//    fun updateNewUserWithGoogleSignIn(
//        context: Context,
//    ) {
//        viewModelScope.launch {
//            try {
//                val user = FirebaseAuth.getInstance().currentUser!!
//                val firebaseUser =
//                    FirebaseUtils.findUserByEmail(user.email!!)
//                if (firebaseUser == null) {
//                    Log.d("GoogleSignInUtils", "fail")
//                    val userModel = UserModel(
//                        fullName = user.displayName,
//                        email = user.email,
//                        uid = user.uid
//                    )
//                    FirebaseUtils.saveUserdata(userModel)
//                }
//            } catch (e: Exception) {
//                _loginState.value = LoginState.Error("Google login failed: ${e.message}")
//                Toast.makeText(context, "Google login failed: ${e.message}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }

}