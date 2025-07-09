package com.nquang.bookingapp.model

import androidx.compose.ui.graphics.Color
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


enum class RoomBookingStatus(val value: String, val color: Color) {
    PENDING("Chờ nhận phòng", Color(0xFF2196F3)),
    CONFIRMED("Hoàn thành", Color(0xFF4CAF50)),
    CANCELLED("Đã hủy", Color(0xFF9E9E9E))
}

enum class RoomBookingType(val value: String) {
    ONLY_DAY("Ban ngày"), // checkIn -> checkIn + 8
    ONLY_NIGHT("Qua đêm"), // checkIn + 9 -> checkIn + 20
    FULL_DAY("Cả ngày") // checkIn -> checkOut
}