package com.example.mainapp.screens.busdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BusDescriptionSection(
    description: String,
    amenities: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Description
        Text(
            text = "Về dịch vụ này",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Gray,
            lineHeight = 20.sp
        )

        // Amenities
        Text(
            text = "Tiện ích",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            amenities.forEach { amenity ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "• ",
                        fontSize = 14.sp,
                        color = Color(0xFF4CAF50)
                    )
                    Text(
                        text = amenity,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
