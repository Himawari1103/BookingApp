package com.example.mainapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainContentSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(top = 32.dp)
    ) {
        RecentlyViewedSection(navController)
        Spacer(modifier = Modifier.height(32.dp))
        WhereToGoSection(navController)
        Spacer(modifier = Modifier.height(32.dp))
    }
}
