package com.nquang.bookingapp.mainapp.screens.roomlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookingScheduleCard() {
    // Determine if it's day or night booking based on time
    val isNightBooking = true // This would be determined by actual booking time

    val cardColor = if (isNightBooking) {
        Color(0xFFF0F0FF) // Light purple for night
    } else {
        Color(0xFFFFF4E6) // Light orange for day
    }

    val bookingType = if (isNightBooking) "Qua đêm" else "Theo giờ"
    val duration = if (isNightBooking) "01 đêm" else "02 giờ"
    val checkInTime = if (isNightBooking) "22:00, 04/06" else "16:30, 05/06"
    val checkOutTime = if (isNightBooking) "12:00, 05/06" else "18:30, 05/06"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp), // Add spacing from header
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp // Make it more prominent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top row with booking type and change button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        if (isNightBooking) Icons.Default.Schedule else Icons.Default.AccessTime,
                        contentDescription = "Booking type",
                        tint = Color(0xFF6B6B6B),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = bookingType,
                        fontSize = 14.sp,
                        color = Color(0xFF6B6B6B),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = duration,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Text(
                    text = "Thay đổi",
                    fontSize = 14.sp,
                    color = Color(0xFFFF6B35),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            // Time details row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Nhận phòng",
                        fontSize = 12.sp,
                        color = Color(0xFF6B6B6B)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = checkInTime,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "To",
                    tint = Color(0xFF6B6B6B),
                    modifier = Modifier.size(20.dp)
                )

                Column {
                    Text(
                        text = "Trả phòng",
                        fontSize = 12.sp,
                        color = Color(0xFF6B6B6B)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = checkOutTime,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
