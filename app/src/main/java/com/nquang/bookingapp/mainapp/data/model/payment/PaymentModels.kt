package com.example.mainapp.data.model.payment

data class BookingInfo(
    val hotelName: String,
    val roomName: String,
    val address: String,
    val checkInDate: String,
    val checkInTime: String,
    val checkOutDate: String,
    val checkOutTime: String,
    val duration: String,
    val isNightBooking: Boolean
)

data class GuestInfo(
    val phoneNumber: String,
    val fullName: String
)

data class PaymentMethod(
    val id: String,
    val name: String,
    val icon: Int,
    val description: String? = null,
    val isEnabled: Boolean = true
)

data class PaymentSummary(
    val roomPrice: String,
    val totalAmount: String
)
