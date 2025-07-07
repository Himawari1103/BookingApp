package com.nquang.bookingapp.mainapp.data.model.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

object CommonMockData {
    
    fun getBottomNavItems(): List<BottomNavItem> {
        return listOf(
            BottomNavItem("Tìm kiếm", Icons.Default.Search, true, "home"),
            BottomNavItem("Yêu thích", Icons.Default.Favorite, false, "favorites"),
            BottomNavItem("Đặt phòng", Icons.Default.BookOnline, false, "bookings"),
            BottomNavItem("Tài khoản", Icons.Default.Person, false, "profile")
        )
    }
}
