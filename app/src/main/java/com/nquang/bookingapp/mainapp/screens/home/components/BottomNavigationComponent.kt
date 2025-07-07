package com.nquang.bookingapp.mainapp.screens.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.model.common.BottomNavItem


@Composable
fun BottomNavigationComponent(modifier: Modifier = Modifier, navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        modifier = modifier,
        tonalElevation = 8.dp
    ) {
        val currentRoute = navController.currentDestination?.route

        val items = listOf(
            BottomNavItem("Trang chủ", Icons.Default.Home, currentRoute == "home", "home"),
            BottomNavItem("Phòng đã đặt", Icons.Default.BookOnline, currentRoute == "bookings", "bookings"),
            BottomNavItem("Tài khoản", Icons.Default.Person, currentRoute == "account", "account")
        )

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        tint = if (item.isSelected) Color(0xFFFF6B35) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        color = if (item.isSelected) Color(0xFFFF6B35) else Color.Gray,
                        fontWeight = if (item.isSelected) FontWeight.Medium else FontWeight.Normal
                    )
                },
                selected = item.isSelected,
                onClick = {
                    when (item.route) {
                        "home" -> {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                        "bookings" -> {
                            navController.navigate("bookings")
                        }
                        "account" -> {
                            // Navigate to account screen when implemented
                            navController.navigate("account")
                        }
                    }
                }
            )
        }
    }
}
