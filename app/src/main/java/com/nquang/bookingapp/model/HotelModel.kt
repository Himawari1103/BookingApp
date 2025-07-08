package com.nquang.bookingapp.model

import java.time.LocalDateTime
import java.time.LocalTime

data class HotelModelGet(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val rating: Float,
    val reviewCount: Int,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val thumbnailImages: List<String>? = null, // url to images
    val amenities: List<String>? = null, // services
    val description: String = "",
    val policies: HotelPolicies,
    val reviews: List<HotelReview>? = null,
    val roomList: List<RoomModelGet>
)

data class HotelModelSet(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val rating: Float,
    val reviewCount: Int,
    val address: String,
    val longitude: Double,
    val latitude: Double,
    val thumbnailImages: List<String>? = null, // url to images
    val amenities: List<String>? = null, // services
    val description: String = "",
    val policies: HotelPolicies,
    val reviews: List<String>? = null, // list IdReviewModel
    val roomList: List<String> // list IdRoomModel
)

data class HotelPolicies(
    val checkIn: LocalTime,
    val checkOut: LocalTime,
    val otherPolicy: String?,
)

data class HotelReview(
    val id: String,
    val userId: String,
    val rating: Float,
    val date: LocalDateTime,
//    val helpful: Int = 0,
    val comment: String? = null
)