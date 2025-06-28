package com.example.mainapp.data.model.tourismdetail

data class TourismActivity(
    val id: String,
    val name: String,
    val category: String,
    val location: String,
    val description: String,
    val rating: Double,
    val reviewCount: String,
    val bookingCount: String,
    val price: Int,
    val originalPrice: Int? = null,
    val imageRes: Int,
    val tags: List<String> = emptyList(),
    val isAvailableToday: Boolean = true,
    val isFavorite: Boolean = false
)

data class TourismFilter(
    val priceRange: IntRange? = null,
    val categories: List<String> = emptyList(),
    val minRating: Double? = null,
    val availableToday: Boolean = false
)
