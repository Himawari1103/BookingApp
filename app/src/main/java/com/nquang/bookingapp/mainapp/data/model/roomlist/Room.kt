package com.nquang.bookingapp.mainapp.data.model.roomlist

data class Room(
    val id: String,
    val name: String,
    val roomNumbers: String,
    val bedType: String,
    val amenities: List<String>,
    val price: String,
    val originalPrice: Int,
    val discount: Int,
    val rewardPoints: Int,
    val images: List<Int>
)
