package com.nquang.bookingapp.mainapp.screens.transportation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.transportation.TransportCategory
import com.nquang.bookingapp.mainapp.data.model.transportation.TransportType

@Composable
fun TransportCategoryTabs(
    categories: List<TransportCategory>,
    selectedCategory: TransportType,
    onCategorySelected: (TransportType) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            TransportCategoryTab(
                category = category,
                isSelected = category.type == selectedCategory,
                onClick = { onCategorySelected(category.type) }
            )
        }
    }
}

@Composable
fun TransportCategoryTab(
    category: TransportCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (icon, iconColor) = when (category.type) {
        TransportType.BUS -> Icons.Default.DirectionsBus to Color(0xFFFF5722)
        TransportType.TRAIN -> Icons.Default.Train to Color(0xFF4CAF50)
        TransportType.CAR_RENTAL -> Icons.Default.DirectionsCar to Color(0xFF2196F3)
        TransportType.AIRPORT_SHUTTLE -> Icons.Default.LocalAirport to Color(0xFF9C27B0)
        TransportType.FLIGHT -> Icons.Default.Flight to Color(0xFFFF9800)
        TransportType.PRIVATE_CAR -> Icons.Default.CarRental to Color(0xFF607D8B)
    }

    Card(
        modifier = Modifier
            .clickable { onClick() }
            .width(100.dp)
            .height(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.White else Color.White.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon container với background tròn như CategorySection
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isSelected) iconColor else Color(0xFFBDBDBD)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = category.title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color(0xFF333333) else Color.White,
                    fontSize = 11.sp
                ),
                maxLines = 2
            )
        }
    }
}
