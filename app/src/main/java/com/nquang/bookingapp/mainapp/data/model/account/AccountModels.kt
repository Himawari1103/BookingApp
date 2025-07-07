package com.nquang.bookingapp.mainapp.data.model.account

data class User(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val avatarUrl: String? = null,
    val nickname: String? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val referralCode: String? = null
)

data class ConnectedAccount(
    val id: String,
    val name: String,
    val icon: Int,
    val isConnected: Boolean
)

data class MenuItem(
    val id: String,
    val title: String,
    val icon: Int,
    val value: String? = null,
    val hasArrow: Boolean = true,
    val isWarning: Boolean = false
)

data class DeletionReason(
    val id: String,
    val text: String
)
