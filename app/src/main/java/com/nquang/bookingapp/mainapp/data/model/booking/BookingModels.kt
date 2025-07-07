package com.nquang.bookingapp.mainapp.data.model.booking

import androidx.compose.ui.graphics.Color

data class BookingItem(
    val id: String,
    val bookingCode: String,
    val hotelName: String,
    val roomType: String,
    val checkInDate: String,
    val checkInTime: String,
    val price: String,
    val paymentMethod: PaymentMethodType,
    val status: BookingStatus,
    val hotelImage: Int
)

enum class BookingStatus(val displayName: String, val color: Color) {
    WAITING_CHECKIN("Chờ nhận phòng", Color(0xFF2196F3)),
    COMPLETED("Hoàn thành", Color(0xFF4CAF50)),
    CANCELLED("Đã hủy", Color(0xFF9E9E9E))
}

enum class PaymentMethodType(val displayName: String, val icon: Int) {
    HOTEL_PAYMENT("Thanh toán tại khách sạn", com.nquang.bookingapp.R.drawable.ic_hotel),
    MOMO("Ví MoMo", com.nquang.bookingapp.R.drawable.ic_momo),
    ZALOPAY("Ví ZaloPay", com.nquang.bookingapp.R.drawable.ic_zalopay),
    CREDIT_CARD("Thẻ tín dụng", com.nquang.bookingapp.R.drawable.ic_credit_card)
}
