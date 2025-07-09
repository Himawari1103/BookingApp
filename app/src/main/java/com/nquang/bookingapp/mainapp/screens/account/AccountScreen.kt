package com.nquang.bookingapp.mainapp.screens.account

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.repository.AccountRepository
import com.nquang.bookingapp.mainapp.screens.account.components.*
import com.nquang.bookingapp.mainapp.screens.home.components.BottomNavigationComponent
import com.nquang.bookingapp.viewmodel.HotelViewModel
import com.nquang.bookingapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    onLogout: () -> Unit,
    hotelViewModel: HotelViewModel,
) {
//    val user = AccountRepository.getCurrentUser()
    val userModel = userViewModel.userModel.value
    if (userModel == null) {
        Log.d("AccountScreen", "User model is null")
        return
    }

    val myPageItems = AccountRepository.getMyPageMenuItems()
    val settingsItems = AccountRepository.getSettingsMenuItems()
    val infoItems = AccountRepository.getInfoMenuItems()

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.statusBarsPadding())

            // Header with user info
            AccountHeader(
                userModel = userModel,
                onEditClick = {
                    navController.navigate("profile")
                },
                onSettingsClick = {
//                    navController.navigate("account_settings")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // My Page section
            MenuSection(
                title = "Trang của tôi",
                items = myPageItems,
                onItemClick = { item ->
                    when (item.id) {
                        "bookings" -> navController.navigate("bookings")
                        "favorites" -> {
                            // Navigate to favorites
                            navController.navigate("favorites")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Settings section
//            MenuSection(
//                title = "Cài đặt",
//                items = settingsItems,
//                onItemClick = { item ->
//                    when (item.id) {
//                        "notifications" -> {
//                            // Navigate to notifications settings
//                        }
//
//                        "language" -> {
//                            // Navigate to language settings
//                        }
//
//                        "location" -> {
//                            // Navigate to location settings
//                        }
//                    }
//                }
//            )

            Spacer(modifier = Modifier.height(24.dp))

            // Info section
            MenuSection(
                title = "Thông tin",
                items = infoItems,
                onItemClick = { item ->
                    when (item.id) {
                        "help" -> {
                            // Navigate to help
                        }

                        "terms" -> {
                            // Navigate to terms
                        }

                        "version" -> {
                            // Show version info
                        }

                        "contact" -> {
                            // Navigate to contact
                        }

                        "logout" -> {
                            // Handle logout
//                            navController.navigate("home") {
//                                popUpTo("home") { inclusive = true }
//                            }
                            onLogout.invoke()
                        }
                    }
                }
            )

            // Bottom spacing for navigation
            Spacer(modifier = Modifier.height(100.dp))
        }

        // Bottom Navigation
        BottomNavigationComponent(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
