package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.R
import com.nquang.bookingapp.mainapp.data.model.common.Hotel

object HotelRepository {

    private val hotels = listOf(
        Hotel(
            id = "1",
            name = "Grand Hotel Saigon",
            rating = 4.8f,
            reviewCount = 1234,
            address = "8 Đồng Khởi, Quận 1, TP.HCM",
            distance = "2.1km từ trung tâm",
            price = "2,500,000",
            originalPrice = "3,200,000",
            imageRes = R.drawable.hotel1,
            imageCount = 25,
            thumbnailImages = listOf(
                R.drawable.hotel4,
                R.drawable.hotel2,
                R.drawable.hotel3
            ),
            amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym", "Nhà hàng")
        ),
        Hotel(
            id = "2",
            name = "Lotte Hotel Hanoi",
            rating = 4.7f,
            reviewCount = 987,
            address = "54 Liễu Giai, Ba Đình, Hà Nội",
            distance = "1.5km từ trung tâm",
            price = "3,800,000",
            originalPrice = "4,500,000",
            imageRes = R.drawable.hotel2,
            imageCount = 32,
            thumbnailImages = listOf(
                R.drawable.hotel1,
                R.drawable.hotel3,
                R.drawable.hotel4
            ),
            amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym", "Bar")
        )
    )

    fun getHotelById(id: String): Hotel {
        return hotels.find { it.id == id } ?: hotels.first()
    }

    fun getAllHotels(): List<Hotel> {
        return hotels
    }
}
