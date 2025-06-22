package com.example.mainapp.screens.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.payment.BookingInfo

@Composable
fun BookingScheduleSection(bookingInfo: BookingInfo) {
    val cardColor = if (bookingInfo.isNightBooking) {
        Color(0xFFF0F0FF) // Light purple for night
    } else {
        Color(0xFFFF6B35) // Orange for day - matching image 2
    }

    // Schedule card matching the design in image 2
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side - Icon and duration
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(60.dp)
            ) {
                Icon(
                    if (bookingInfo.isNightBooking) Icons.Default.Schedule else Icons.Default.AccessTime,
                    contentDescription = "Duration",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = bookingInfo.duration,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right side - Time details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Check-in
                Text(
                    text = "Nhận phòng",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Text(
                    text = "${bookingInfo.checkInTime} • ${bookingInfo.checkInDate}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Check-out
                Text(
                    text = "Trả phòng",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Text(
                    text = "${bookingInfo.checkOutTime} • ${bookingInfo.checkOutDate}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }

    // Reduced spacing
    Spacer(modifier = Modifier.height(16.dp))
}
