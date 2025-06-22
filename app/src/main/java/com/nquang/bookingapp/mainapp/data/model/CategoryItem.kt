package com.example.mainapp.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryItem(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val id: String
)
