package com.example.mainapp.data.model.booking

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

enum class BookingStatus(val displayName: String, val color: androidx.compose.ui.graphics.Color) {
    WAITING_CHECKIN("Chờ nhận phòng", androidx.compose.ui.graphics.Color(0xFF2196F3)),
    COMPLETED("Hoàn thành", androidx.compose.ui.graphics.Color(0xFF4CAF50)),
    CANCELLED("Đã hủy", androidx.compose.ui.graphics.Color(0xFF9E9E9E))
}

enum class PaymentMethodType(val displayName: String, val icon: Int) {
    HOTEL_PAYMENT("Thanh toán tại khách sạn", com.example.mainapp.R.drawable.ic_hotel),
    MOMO("Ví MoMo", com.example.mainapp.R.drawable.ic_momo),
    ZALOPAY("Ví ZaloPay", com.example.mainapp.R.drawable.ic_zalopay),
    CREDIT_CARD("Thẻ tín dụng", com.example.mainapp.R.drawable.ic_credit_card)
}
