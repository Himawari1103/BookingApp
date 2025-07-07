package com.nquang.bookingapp.mainapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HeaderSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF9A56),
                        Color(0xFFFFB366),
                        Color(0xFFFFC777)
                    )
                )
            )
    ) {
        CategorySection(navController)
        Spacer(modifier = Modifier.height(20.dp))
    }
}
