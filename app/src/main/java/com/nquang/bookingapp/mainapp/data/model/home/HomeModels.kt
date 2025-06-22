package com.example.mainapp.data.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class QuickFilter(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val id: String
)

data class SearchFilter(
    val location: String,
    val checkIn: String,
    val checkOut: String,
    val guests: Int,
    val rooms: Int
)

data class FeaturedHotel(
    val id: String,
    val name: String,
    val rating: Float,
    val price: String,
    val originalPrice: String,
    val imageRes: Int,
    val discount: String? = null
)
