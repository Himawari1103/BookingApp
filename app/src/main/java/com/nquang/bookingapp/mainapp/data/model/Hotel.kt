package com.example.mainapp.data.model

data class Hotel(
    val id: String,
    val name: String,
    val rating: Float,
    val reviewCount: Int,
    val address: String,
    val distance: String,
    val price: String,
    val originalPrice: String,
    val mainImageRes: Int,
    val imageCount: Int,
    val thumbnailImages: List<Int>,
    val amenities: List<String> = emptyList()
)
