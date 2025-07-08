package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.R
import com.nquang.bookingapp.mainapp.data.model.account.MenuItem

object AccountRepository {

//    fun getCurrentUser(): User {
//        return User(
//            id = "1",
//            name = "Giang",
//            phone = "+84 986296088",
//            email = "haivann930@gmail.com",
//            nickname = "Giang",
//            referralCode = "GJ123456"
//        )
//    }

//    fun getConnectedAccounts(): List<ConnectedAccount> {
//        return listOf(
//            ConnectedAccount("facebook", "Facebook", R.drawable.ic_facebook, false),
//            ConnectedAccount("google", "Google", R.drawable.ic_google, true),
//            ConnectedAccount("apple", "Apple ID", R.drawable.ic_apple, false)
//        )
//    }

    fun getMyPageMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem(
                id = "bookings",
                title = "Đặt phòng của tôi",
                icon = R.drawable.ic_booking
            ),
            MenuItem(
                id = "favorites",
                title = "Khách sạn yêu thích",
                icon = R.drawable.ic_heart
            )
        )
    }

    fun getSettingsMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem(
                id = "notifications",
                title = "Thông báo",
                icon = R.drawable.ic_notification
            ),
            MenuItem(
                id = "language",
                title = "Ngôn ngữ",
                icon = R.drawable.ic_language,
                value = "Tiếng Việt"
            ),
            MenuItem(
                id = "location",
                title = "Khu vực",
                icon = R.drawable.ic_location,
                value = "Hà Nội"
            )
        )
    }

    fun getInfoMenuItems(): List<MenuItem> {
        return listOf(
//            MenuItem(
//                id = "help",
//                title = "Hỏi đáp",
//                icon = R.drawable.ic_help
//            ),
//            MenuItem(
//                id = "terms",
//                title = "Điều khoản & Chính sách",
//                icon = R.drawable.ic_policy
//            ),
//            MenuItem(
//                id = "version",
//                title = "Phiên bản",
//                icon = R.drawable.ic_info,
//                value = "15.68.0",
//                hasArrow = false
//            ),
//            MenuItem(
//                id = "contact",
//                title = "Liên hệ",
//                icon = R.drawable.ic_contact
//            ),
            MenuItem(
                id = "logout",
                title = "Đăng xuất",
                icon = R.drawable.ic_logout,
                isWarning = true
            )
        )
    }

//    fun updateUserProfile(user: User): Boolean {
//        // Simulate API call
//        return true
//    }
//
//    fun updateConnectedAccount(accountId: String, isConnected: Boolean): Boolean {
//        // Simulate API call
//        return true
//    }
//
//    fun deleteAccount(reason: String): Boolean {
//        // Simulate API call
//        return true
//    }
}
