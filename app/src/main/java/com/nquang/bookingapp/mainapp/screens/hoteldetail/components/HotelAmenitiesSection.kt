package com.example.mainapp.screens.hoteldetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Amenity(
    val name: String,
    val icon: ImageVector
)

@Composable
fun HotelAmenitiesSection() {
    var showAllAmenities by remember { mutableStateOf(false) }

    val amenities = listOf(
        Amenity("Thang máy", Icons.Default.Elevator),
        Amenity("Bãi đỗ xe ô tô", Icons.Default.LocalParking),
        Amenity("Wi-Fi miễn phí", Icons.Default.Wifi),
        Amenity("Spa/Sauna", Icons.Default.Spa),
        Amenity("Phòng gym", Icons.Default.FitnessCenter),
        Amenity("Nhà hàng", Icons.Default.Restaurant),
        Amenity("Dịch vụ phòng", Icons.Default.RoomService),
        Amenity("Lễ tân 24h", Icons.Default.Schedule)
    )

    val displayedAmenities = if (showAllAmenities) amenities else amenities.take(4)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Header với thanh màu cam bên trái
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            // Thanh màu cam bên trái
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(
                        Color(0xFFFF6B35),
                        RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Tiện ích khách sạn",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(displayedAmenities) { amenity ->
                AmenityCard(amenity = amenity)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (showAllAmenities) "Thu gọn" else "Xem tất cả",
            fontSize = 16.sp,
            color = Color(0xFFFF6B35),
            modifier = Modifier
                .clickable { showAllAmenities = !showAllAmenities }
                .padding(vertical = 8.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
fun AmenityCard(amenity: Amenity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    Color(0xFFF5F5F5),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = amenity.icon,
                contentDescription = amenity.name,
                tint = Color(0xFFFF6B35),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = amenity.name,
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            lineHeight = 14.sp
        )
    }
}
