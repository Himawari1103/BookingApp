package com.nquang.bookingapp.mainapp.data.model.common

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val route: String
)

data class Hotel(
    val id: String,
    val name: String,
    val rating: Float,
    val reviewCount: Int,
    val address: String,
    val distance: String,
    val price: String,
    val originalPrice: String,
    val imageRes: Int,
    val imageCount: Int,
    val thumbnailImages: List<Int>,
    val amenities: List<String> = emptyList()
)
