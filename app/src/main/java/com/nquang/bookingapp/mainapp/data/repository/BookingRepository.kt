package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.R
import com.nquang.bookingapp.mainapp.data.model.booking.BookingItem
import com.nquang.bookingapp.mainapp.data.model.booking.BookingStatus
import com.nquang.bookingapp.mainapp.data.model.booking.PaymentMethodType

object BookingRepository {
    
    fun getAllBookings(): List<BookingItem> {
        return listOf(
            BookingItem(
                id = "1",
                bookingCode = "3610909",
                hotelName = "Dodo Home 3",
                roomType = "Theo giờ | Deluxe Double Room",
                checkInDate = "07/06/2025",
                checkInTime = "01:19",
                price = "320.000đ",
                paymentMethod = PaymentMethodType.HOTEL_PAYMENT,
                status = BookingStatus.WAITING_CHECKIN,
                hotelImage = R.drawable.hotel1
            ),
            BookingItem(
                id = "2",
                bookingCode = "3233957",
                hotelName = "Cici Mini Hotel",
                roomType = "Qua đêm | MINI ROOM - 201",
                checkInDate = "08/01/2025",
                checkInTime = "12:10",
                price = "269.100đ",
                paymentMethod = PaymentMethodType.HOTEL_PAYMENT,
                status = BookingStatus.COMPLETED,
                hotelImage = R.drawable.hotel2
            ),
            BookingItem(
                id = "3",
                bookingCode = "3231726",
                hotelName = "Cici Mini Hotel",
                roomType = "Qua đêm | MINI ROOM - 301",
                checkInDate = "07/01/2025",
                checkInTime = "09:58",
                price = "269.100đ",
                paymentMethod = PaymentMethodType.HOTEL_PAYMENT,
                status = BookingStatus.CANCELLED,
                hotelImage = R.drawable.hotel3
            ),
            BookingItem(
                id = "4",
                bookingCode = "3145678",
                hotelName = "Grand Hotel Saigon",
                roomType = "Theo ngày | Superior Room",
                checkInDate = "15/06/2025",
                checkInTime = "14:00",
                price = "1.250.000đ",
                paymentMethod = PaymentMethodType.MOMO,
                status = BookingStatus.WAITING_CHECKIN,
                hotelImage = R.drawable.hotel4
            ),
            BookingItem(
                id = "5",
                bookingCode = "3098765",
                hotelName = "Lotte Hotel Hanoi",
                roomType = "Qua đêm | Deluxe Room",
                checkInDate = "20/05/2025",
                checkInTime = "22:00",
                price = "1.800.000đ",
                paymentMethod = PaymentMethodType.ZALOPAY,
                status = BookingStatus.COMPLETED,
                hotelImage = R.drawable.hotel5
            )
        )
    }
    
    fun getBookingsByStatus(status: BookingStatus): List<BookingItem> {
        return getAllBookings().filter { it.status == status }
    }
    
    fun deleteBooking(bookingId: String): Boolean {
        // Simulate deletion - in real app, this would call API
        return true
    }
    
    fun reportError(bookingId: String): Boolean {
        // Simulate error reporting - in real app, this would call API
        return true
    }
}
