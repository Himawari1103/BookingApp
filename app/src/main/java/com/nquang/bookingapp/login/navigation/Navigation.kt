package com.example.login.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login.screens.*

// Define navigation routes
object AppScreens {
    const val LOGIN_SCREEN = "login_screen"
    const val REGISTER_SCREEN = "register_screen"
    const val FORGOT_PASSWORD_EMAIL_SCREEN = "forgot_password_email_screen"
    const val OTP_VERIFICATION_SCREEN = "otp_verification_screen/{email}"
    const val RESET_PASSWORD_SCREEN = "reset_password_screen"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.LOGIN_SCREEN,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppScreens.LOGIN_SCREEN) {
            LoginScreen(
                onLoginClick = {
                    // Handle login logic here
                    println("Login button clicked")
                },
                onRegisterClick = {
                    // Navigate to register screen
                    navController.navigate(AppScreens.REGISTER_SCREEN)
                },
                onGoogleSignInClick = {
                    // Handle Google sign-in
                    println("Google sign-in clicked")
                },
                onForgotPasswordClick = {
                    // Navigate to forgot password screen
                    navController.navigate(AppScreens.FORGOT_PASSWORD_EMAIL_SCREEN)
                }
            )
        }

        composable(AppScreens.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterClick = {
                    // Handle registration logic
                    println("Register button clicked")
                    // After successful registration, navigate back to login
                    navController.navigate(AppScreens.LOGIN_SCREEN) {
                        // Clear the back stack so user can't go back to register screen
                        popUpTo(AppScreens.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onBackToLoginClick = {
                    // Navigate back to login screen
                    navController.navigateUp()
                },
                onGoogleSignInClick = {
                    // Handle Google sign-up
                    println("Google sign-up clicked")
                }
            )
        }

        composable(AppScreens.FORGOT_PASSWORD_EMAIL_SCREEN) {
            ForgotPasswordEmailScreen(
                onSendClick = { email ->
                    // Navigate to OTP verification screen with email
                    navController.navigate("otp_verification_screen/$email")
                },
                onBackClick = {
                    // Navigate back to login screen
                    navController.navigateUp()
                }
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