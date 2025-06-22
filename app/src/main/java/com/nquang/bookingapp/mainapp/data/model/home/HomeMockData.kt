package com.example.mainapp.data.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.example.mainapp.R
import com.example.mainapp.ui.theme.*
import com.example.mainapp.data.common.Hotel

object HomeMockData {
    
    fun getQuickFilters(): List<QuickFilter> {
        return listOf(
            QuickFilter("Khách sạn 5 sao", Icons.Default.Star, Warning, "5star"),
            QuickFilter("Gần biển", Icons.Default.Waves, Blue500, "beach"),
            QuickFilter("Có hồ bơi", Icons.Default.Pool, Blue600, "pool"),
            QuickFilter("Trung tâm thành phố", Icons.Default.LocationCity, Success, "city"),
            QuickFilter("Resort", Icons.Default.Villa, Color(0xFF9C27B0), "resort"),
            QuickFilter("Có spa", Icons.Default.Spa, Color(0xFFE91E63), "spa")
        )
    }
    
    fun getFeaturedHotels(): List<FeaturedHotel> {
        return listOf(
            FeaturedHotel(
                id = "1",
                name = "Grand Hotel Saigon",
                rating = 4.8f,
                price = "2,500,000",
                originalPrice = "3,200,000",
                imageRes = R.drawable.hotel1,
                discount = "22% OFF"
            ),
            FeaturedHotel(
                id = "2",
                name = "Lotte Hotel Hanoi",
                rating = 4.7f,
                price = "3,800,000",
                originalPrice = "4,500,000",
                imageRes = R.drawable.hotel2,
                discount = "16% OFF"
            )
        )
    }
    
    fun getRecommendedHotels(): List<Hotel> {
        return listOf(
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
                thumbnailImages = listOf(R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4),
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
                thumbnailImages = listOf(R.drawable.hotel1, R.drawable.hotel3, R.drawable.hotel4),
                amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym", "Bar")
            ),
            Hotel(
                id = "3",
                name = "InterContinental Danang",
                rating = 4.9f,
                reviewCount = 756,
                address = "Bãi Bắc, Sơn Trà, Đà Nẵng",
                distance = "5.2km từ trung tâm",
                price = "4,200,000",
                originalPrice = "5,000,000",
                imageRes = R.drawable.hotel3,
                imageCount = 18,
                thumbnailImages = listOf(R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel4),
                amenities = listOf("Wi-Fi miễn phí", "Bãi biển riêng", "Spa", "Golf", "Nhà hàng")
            ),
            Hotel(
                id = "4",
                name = "JW Marriott Phu Quoc",
                rating = 4.6f,
                reviewCount = 543,
                address = "Emerald Bay, Phú Quốc, Kiên Giang",
                distance = "8.1km từ sân bay",
                price = "5,500,000",
                originalPrice = "6,800,000",
                imageRes = R.drawable.hotel4,
                imageCount = 28,
                thumbnailImages = listOf(R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel3),
                amenities = listOf("Wi-Fi miễn phí", "Bãi biển riêng", "Spa", "Hồ bơi", "Kids Club")
            )
        )
    }
}
