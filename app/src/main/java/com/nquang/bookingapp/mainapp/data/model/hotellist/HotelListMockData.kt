package com.example.mainapp.data.model.hotellist

import com.example.mainapp.R

object HotelListMockData {

    val hotels = listOf(
        HotelItem(
            id = "1",
            name = "Grand Hotel Saigon",
            location = "Quận 1, TP.HCM",
            rating = 4.8f,
            reviewCount = "1.2K+",
            price = "2,500,000",
            originalPrice = "3,200,000",
            imageRes = R.drawable.hotel1,
            amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym"),
            distance = "2.1km từ trung tâm"
        ),
        HotelItem(
            id = "2",
            name = "Lotte Hotel Hanoi",
            location = "Ba Đình, Hà Nội",
            rating = 4.7f,
            reviewCount = "987",
            price = "3,800,000",
            originalPrice = "4,500,000",
            imageRes = R.drawable.hotel2,
            amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Bar"),
            distance = "1.5km từ trung tâm",
            isFavorite = false
        ),
        HotelItem(
            id = "3",
            name = "InterContinental Danang",
            location = "Sơn Trà, Đà Nẵng",
            rating = 4.9f,
            reviewCount = "2.1K+",
            price = "4,200,000",
            imageRes = R.drawable.hotel3,
            amenities = listOf("Bãi biển riêng", "Spa", "Nhà hàng", "Hồ bơi"),
            distance = "5km từ sân bay",
            isFavorite = true
        ),
        HotelItem(
            id = "4",
            name = "JW Marriott Phu Quoc",
            location = "Phú Quốc, Kiên Giang",
            rating = 4.6f,
            reviewCount = "856",
            price = "5,500,000",
            originalPrice = "6,800,000",
            imageRes = R.drawable.hotel4,
            amenities = listOf("Resort biển", "Spa", "Golf", "Nhà hàng"),
            distance = "Bãi biển Ong Lang",
            isFavorite = true
        ),
        HotelItem(
            id = "5",
            name = "Park Hyatt Saigon",
            location = "Quận 1, TP.HCM",
            rating = 4.8f,
            reviewCount = "1.5K+",
            price = "6,200,000",
            imageRes = R.drawable.hotel5,
            amenities = listOf("Luxury", "Spa", "Rooftop bar", "Concierge"),
            distance = "0.5km từ trung tâm",
            isFavorite = true
        ),
        HotelItem(
            id = "6",
            name = "Sheraton Hanoi Hotel",
            location = "Hoàn Kiếm, Hà Nội",
            rating = 4.5f,
            reviewCount = "743",
            price = "2,800,000",
            originalPrice = "3,500,000",
            imageRes = R.drawable.hotel6,
            amenities = listOf("Wi-Fi", "Fitness center", "Restaurant", "Bar"),
            distance = "1km từ Hồ Gươm",
            isFavorite = true
        ),
        HotelItem(
            id = "7",
            name = "Fusion Resort Nha Trang",
            location = "Nha Trang, Khánh Hòa",
            rating = 4.7f,
            reviewCount = "1.1K+",
            price = "3,900,000",
            imageRes = R.drawable.hotel7,
            amenities = listOf("All-inclusive spa", "Beachfront", "Pool", "Restaurant"),
            distance = "Bãi biển Nha Trang",
            isFavorite = false
        ),
        HotelItem(
            id = "8",
            name = "Anantara Hoi An Resort",
            location = "Hội An, Quảng Nam",
            rating = 4.8f,
            reviewCount = "967",
            price = "4,800,000",
            originalPrice = "5,600,000",
            imageRes = R.drawable.hotel8,
            amenities = listOf("Riverside", "Spa", "Cultural tours", "Fine dining"),
            distance = "2km từ phố cổ",
            isFavorite = false
        ),
        HotelItem(
            id = "9",
            name = "Vinpearl Resort Da Lat",
            location = "Đà Lạt, Lâm Đồng",
            rating = 4.6f,
            reviewCount = "1.3K+",
            price = "3,200,000",
            imageRes = R.drawable.hotel9,
            amenities = listOf("Golf course", "Spa", "Adventure park", "Restaurant"),
            distance = "5km từ trung tâm",
            isFavorite = false
        ),
        HotelItem(
            id = "10",
            name = "Pullman Vung Tau",
            location = "Vũng Tàu, Bà Rịa-Vũng Tàu",
            rating = 4.4f,
            reviewCount = "689",
            price = "2,200,000",
            originalPrice = "2,800,000",
            imageRes = R.drawable.hotel10,
            amenities = listOf("Beachfront", "Pool", "Spa", "Restaurant"),
            distance = "Bãi biển Thùy Vân",
            isFavorite = false
        ),
        HotelItem(
            id = "11",
            name = "Sofitel Legend Metropole",
            location = "Hoàn Kiếm, Hà Nội",
            rating = 4.9f,
            reviewCount = "2.3K+",
            price = "7,500,000",
            imageRes = R.drawable.hotel11,
            amenities = listOf("Historic luxury", "Spa", "Fine dining", "Opera house"),
            distance = "0.3km từ Hồ Gươm",
            isFavorite = false
        ),
        HotelItem(
            id = "12",
            name = "Banyan Tree Lang Co",
            location = "Huế, Thừa Thiên Huế",
            rating = 4.7f,
            reviewCount = "845",
            price = "5,800,000",
            originalPrice = "7,200,000",
            imageRes = R.drawable.hotel12,
            amenities = listOf("Beachfront villas", "Spa", "Golf", "Private beach"),
            distance = "Bãi biển Lăng Cô",
            isFavorite = false
        ),
        HotelItem(
            id = "13",
            name = "Melia Ba Vi Mountain Retreat",
            location = "Ba Vì, Hà Nội",
            rating = 4.5f,
            reviewCount = "567",
            price = "2,900,000",
            imageRes = R.drawable.hotel13,
            amenities = listOf("Mountain view", "Spa", "Hiking", "Restaurant"),
            distance = "45km từ Hà Nội",
            isFavorite = false
        ),
        HotelItem(
            id = "14",
            name = "Centara Sandy Beach Resort",
            location = "Đà Nẵng",
            rating = 4.6f,
            reviewCount = "1.4K+",
            price = "3,600,000",
            originalPrice = "4,200,000",
            imageRes = R.drawable.hotel14,
            amenities = listOf("Beachfront", "Water sports", "Spa", "Kids club"),
            distance = "Bãi biển Mỹ Khê",
            isFavorite = false
        ),
        HotelItem(
            id = "15",
            name = "Rex Hotel Saigon",
            location = "Quận 1, TP.HCM",
            rating = 4.3f,
            reviewCount = "892",
            price = "1,800,000",
            imageRes = R.drawable.hotel15,
            amenities = listOf("Historic", "Rooftop bar", "Central location", "Restaurant"),
            distance = "0.2km từ Nhà hát TP",
            isFavorite = true
        )
    )
}
