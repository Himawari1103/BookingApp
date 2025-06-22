package com.example.mainapp.screens.reviews.components

data class ReviewData(
    val id: String,
    val userName: String,
    val date: String,
    val rating: Float,
    val comment: String,
    val images: List<Int> = emptyList(),
    val roomType: String? = null
)

data class SortOption(
    val id: String,
    val name: String
)
