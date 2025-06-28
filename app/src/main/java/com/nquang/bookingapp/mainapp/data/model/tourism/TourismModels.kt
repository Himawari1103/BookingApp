package com.example.mainapp.data.model.tourism

data class Destination(
    val id: String,
    val name: String,
    val province: String,
    val description: String,
    val imageUrl: String = "",
    val isPopular: Boolean = false
)

data class TourismActivity(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val reviewCount: String,
    val imageUrl: String,
    val destination: String,
    val category: String,
    val duration: String
)

enum class TourismCategory(val displayName: String) {
    TOUR("Tour"),
    ENTERTAINMENT("Vui chơi"),
    SPA("Spa & Wellness"),
    FOOD("Ẩm thực"),
    CULTURE("Văn hóa"),
    ADVENTURE("Phiêu lưu"),
    ALL("Tất cả")
}
