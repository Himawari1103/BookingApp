package com.nquang.bookingapp.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nquang.bookingapp.login.forgotpassword.ForgotPasswordEmailScreen
import com.nquang.bookingapp.login.forgotpassword.OtpVerificationScreen
import com.nquang.bookingapp.login.forgotpassword.ResetPasswordScreen
import com.nquang.bookingapp.login.login.LoginScreen
import com.nquang.bookingapp.login.register.RegisterScreen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nquang.bookingapp.utils.GoogleSignInUtils
import com.nquang.bookingapp.login.viewmodel.ForgotPasswordViewModel
import com.nquang.bookingapp.login.viewmodel.LoginViewModel
import com.nquang.bookingapp.login.viewmodel.RegisterViewModel
import com.nquang.bookingapp.model.UserModel
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import kotlinx.coroutines.launch

// Define navigation routes
object AppScreens {
    const val LOGIN_SCREEN = "login_screen"
    const val REGISTER_SCREEN = "register_screen"
    const val FORGOT_PASSWORD_EMAIL_SCREEN = "forgot_password_email_screen"
    const val OTP_VERIFICATION_SCREEN = "otp_verification_screen/{email}"
    const val RESET_PASSWORD_SCREEN = "reset_password_screen"
}

@Composable
fun AuthNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.LOGIN_SCREEN,
    registerViewModel: RegisterViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            GoogleSignInUtils.doGoogleSignIn(
                context = context,
                scope = scope,
                launcher = null,
                login = {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                }
            )

        }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppScreens.LOGIN_SCREEN) {
            LoginScreen(
                onLoginClick = {
                    loginViewModel.loginWithEmail(context) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                },
                onRegisterClick = {
                    // Navigate to register screen
                    navController.navigate(AppScreens.REGISTER_SCREEN)
                },
                onGoogleSignInClick = {
                    loginViewModel.loginWithGoogle(
                        context, scope, launcher
                    ) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                },
                onForgotPasswordClick = {
                    // Navigate to forgot password screen
                    navController.navigate(AppScreens.FORGOT_PASSWORD_EMAIL_SCREEN)
                },
                registerViewModel = registerViewModel,
                loginViewModel = loginViewModel
            )
        }

        composable(AppScreens.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterClick = {
                    // Handle registration logic
                    println("Register button clicked")

                    if (registerViewModel.fullName.isEmpty() || registerViewModel.email.isEmpty() || registerViewModel.password.isEmpty() || registerViewModel.confirmPassword.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@RegisterScreen
                    }
                    if (registerViewModel.password != registerViewModel.confirmPassword) {
                        Toast.makeText(
                            context,
                            "Password and confirm password do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@RegisterScreen
                    }
                    if (registerViewModel.password.length < 6) {
                        Toast.makeText(
                            context,
                            "Password must be at least 6 characters",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@RegisterScreen
                    }

                    scope.launch {
                        val statusReg: Boolean = registerViewModel.register()

                        if (statusReg) {
                            Toast.makeText(
                                context,
                                "Registration successful: " + registerViewModel.authState.value.userEmail,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            // After successful registration, navigate back to login
                            loginViewModel.email = registerViewModel.email
                            navController.navigate(AppScreens.LOGIN_SCREEN) {
                                // Clear the back stack so user can't go back to register screen
                                popUpTo(AppScreens.LOGIN_SCREEN) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                registerViewModel.authState.value.errorMessage,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }

                },
                onBackToLoginClick = {
                    // Navigate back to login screen
                    navController.navigateUp()
                },
                onGoogleSignInClick = {
                    loginViewModel.loginWithGoogle(
                        context, scope, launcher
                    ) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        onLoginSuccess() // Chuyển sang MainNavHost
                    }
                },
                registerViewModel = registerViewModel
            )
        }

        composable(AppScreens.FORGOT_PASSWORD_EMAIL_SCREEN) {
            ForgotPasswordEmailScreen(
                onSendClick = {
                    forgotPasswordViewModel.forgotPassword(context)
                },
                onBackClick = {
                    // Navigate back to login screen
                    navController.navigateUp()
                },
                forgotPasswordViewModel = forgotPasswordViewModel
            )
        }

        composable(
            route = AppScreens.OTP_VERIFICATION_SCREEN,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""

            OtpVerificationScreen(
                email = email,
                onVerifyClick = { otp ->
                    // Handle OTP verification
                    println("OTP verification: $otp")
                    // Navigate to reset password screen
                    navController.navigate(AppScreens.RESET_PASSWORD_SCREEN)
                },
                onBackClick = {
                    // Navigate back to forgot password email screen
                    navController.navigateUp()
                },
                onResendClick = {
                    // Handle resend OTP
                    println("Resend OTP clicked")
                }
            )
        }

        composable(AppScreens.RESET_PASSWORD_SCREEN) {
            ResetPasswordScreen(
                onSubmitClick = { newPassword, confirmPassword ->
                    // Handle password reset
                    println("Password reset: $newPassword, $confirmPassword")
                    // Navigate back to login screen
                    navController.navigate(AppScreens.LOGIN_SCREEN) {
                        // Clear the back stack so user can't go back to reset password flow
                        popUpTo(AppScreens.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onBackClick = {
                    // Navigate back to OTP verification screen
                    navController.navigateUp()
                }
            )
        }
    }
}