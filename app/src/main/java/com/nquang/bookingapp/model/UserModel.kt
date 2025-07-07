package com.nquang.bookingapp.model

data class UserModel (
    val fullName: String? = "",
    val email: String? = "",
    val password: String? = "",
    val phone: String? = "",
    val address: String? = "",
    val uid: String = ""
)