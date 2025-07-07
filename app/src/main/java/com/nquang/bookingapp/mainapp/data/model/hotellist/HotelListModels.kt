package com.example.mainapp.data.model.hotellist

data class HotelItem(
    val id: String,
    val name: String,
    val location: String,
    val rating: Float,
    val reviewCount: String,
    val price: String,
    val originalPrice: String? = null,
    val imageRes: Int,
    val amenities: List<String> = emptyList(),
    val distance: String = "",
    val isFavorite: Boolean = false
)

data class HotelFilter(
    val priceRange: Pair<Int, Int>? = null,
    val rating: Float? = null,
    val amenities: List<String> = emptyList(),
    val sortBy: HotelSortType = HotelSortType.RECOMMENDED
)

enum class HotelSortType {
    RECOMMENDED,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    RATING,
    DISTANCE
}
