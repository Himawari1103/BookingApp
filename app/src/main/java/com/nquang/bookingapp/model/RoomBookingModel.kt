package com.nquang.bookingapp.model

import java.time.LocalDateTime

data class RoomBookingModelGet( // n-n user-room
    val id:String,
    val userId: String,
    val roomId:String,
    val checkInDateTime: LocalDateTime,
    val checkOutDateTime: LocalDateTime,
    val status: RoomBookingStatus,
    val type: RoomBookingType
)

data class RoomBookingModelSet( // n-n user-room
    val id:String,
    val userId: String,
    val roomId:String,
    val checkInDateTime: String,
    val checkOutDateTime: String,
    val status: RoomBookingStatus,
    val type: RoomBookingType
)


enum class RoomBookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}

enum class RoomBookingType {
    ONLY_DAY,
    ONLY_NIGHT,
    FULL_DAY
}