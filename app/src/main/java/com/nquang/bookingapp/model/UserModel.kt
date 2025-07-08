package com.nquang.bookingapp.model

data class UserModel(
    val uid: String = "",
    val email: String? = null,
    val password: String? = null,
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val avatarUrl: String? = null,
    val nickname: String? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val referralCode: String? = null
)