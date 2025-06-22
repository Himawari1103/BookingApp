package com.example.mainapp.screens.bookings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.booking.BookingStatus

@Composable
fun BookingStatusTabs(
    selectedStatus: BookingStatus?,
    onStatusSelected: (BookingStatus?) -> Unit,
    bookingCounts: Map<BookingStatus, Int>
) {
    val tabs = listOf(
        null to "Tất cả",
        BookingStatus.WAITING_CHECKIN to "Chờ nhận phòng",
        BookingStatus.COMPLETED to "Hoàn thành", 
        BookingStatus.CANCELLED to "Đã hủy"
    )
    
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEach { (status, title) ->
                val isSelected = selectedStatus == status
                val count = if (status == null) {
                    bookingCounts.values.sum()
                } else {
                    bookingCounts[status] ?: 0
                }
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onStatusSelected(status) }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color(0xFFFF6B35) else Color.Gray
                    )
                    
                    if (count > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "($count)",
                            fontSize = 12.sp,
                            color = if (isSelected) Color(0xFFFF6B35) else Color.Gray
                        )
                    }
                    
                    if (isSelected) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(2.dp)
                                .background(
                                    Color(0xFFFF6B35),
                                    RoundedCornerShape(1.dp)
                                )
                        )
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
