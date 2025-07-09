package com.nquang.bookingapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.nquang.bookingapp.login.login.LoginScreen
import com.nquang.bookingapp.navigation.AuthNavigation
import com.nquang.bookingapp.login.ui.theme.LoginTheme
import com.nquang.bookingapp.navigation.MainNavigation
import com.nquang.bookingapp.viewmodel.HotelViewModel
import com.nquang.bookingapp.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
                var isAuthenticated by remember { mutableStateOf(false) }
                val userViewModel: UserViewModel = viewModel()
                val hotelViewModel: HotelViewModel = viewModel()

                if (!isAuthenticated) {
//                    val authNavController = rememberNavController()
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AuthNavigation(
//                            navController = authNavController,
                            onLoginSuccess = {
                                isAuthenticated = true
                                userViewModel.fetchUserData()
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                } else {
//                    val mainNavController = rememberNavController()
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MainNavigation(
//                            navController = mainNavController,
                            userViewModel = userViewModel,
                            hotelViewModel = hotelViewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }

            }
        }

    }
}

// Update the preview to show the LoginScreen
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginTheme {
        LoginScreen(
            registerViewModel = viewModel(),
            loginViewModel = viewModel()
        )
    }
}

