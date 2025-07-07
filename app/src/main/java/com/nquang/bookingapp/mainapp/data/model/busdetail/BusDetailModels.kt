package com.nquang.bookingapp.mainapp.data.model.busdetail

data class BusServiceDetail(
    val id: String,
    val name: String,
    val company: String,
    val route: String,
    val rating: Float,
    val reviewCount: Int,
    val price: Int,
    val imageRes: Int,
    val location: String,
    val images: List<Int>,
    val description: String,
    val amenities: List<String>,
    val highlights: List<BusHighlight>
)

data class BusHighlight(
    val title: String,
    val description: String,
    val imageRes: Int
)

data class BusReview(
    val id: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val date: String,
    val userAvatar: String? = null
)
