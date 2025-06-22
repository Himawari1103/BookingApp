package com.example.mainapp.data.hoteldetail

data class HotelDetail(
    val id: String,
    val name: String,
    val rating: Float,
    val reviewCount: Int,
    val address: String,
    val distance: String,
    val price: String,
    val originalPrice: String,
    val imageRes: Int,
    val imageCount: Int,
    val thumbnailImages: List<Int>,
    val amenities: List<String>,
    val description: String,
    val reviews: List<Review>,
    val policies: HotelPolicies
)

data class Review(
    val id: String,
    val userName: String,
    val userAvatar: Int?,
    val rating: Float,
    val comment: String,
    val date: String,
    val helpful: Int = 0
)

data class HotelPolicies(
    val checkIn: String,
    val checkOut: String,
    val cancellation: String,
    val childPolicy: String,
    val petPolicy: String
)

data class BookingInfo(
    val checkInDate: String,
    val checkOutDate: String,
    val guests: Int,
    val rooms: Int,
    val totalPrice: String,
    val taxes: String
)
