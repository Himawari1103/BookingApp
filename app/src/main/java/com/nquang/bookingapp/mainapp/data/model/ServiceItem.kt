package com.nquang.bookingapp.mainapp.data.model

data class ServiceItem(
    val id: String,
    val title: String,
    val location: String,
    val rating: Float,
    val reviewCount: Int,
    val price: String,
    val type: ServiceType,
    val imageRes: Int,
    val badge: String? = null
)

enum class ServiceType {
    VISA,
    TOUR,
    ENTERTAINMENT,
    EXPERIENCE
}
