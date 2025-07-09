package com.nquang.bookingapp.mainapp.screens.bookingdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nquang.bookingapp.mainapp.data.model.booking.BookingItem
import com.nquang.bookingapp.model.RoomBookingModelGet

@Composable
fun BookingDetailMenuDialog(
    booking: RoomBookingModelGet,
    onDismiss: () -> Unit,
    onDeleteHistory: (RoomBookingModelGet) -> Unit,
    onReportError: (RoomBookingModelGet) -> Unit,
    onCancellationPolicy: (RoomBookingModelGet) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Delete history option
                MenuOption(
                    icon = Icons.Default.Delete,
                    text = "Xóa lịch sử đặt phòng",
                    onClick = {
                        onDeleteHistory(booking)
                        onDismiss()
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Report error option
                MenuOption(
                    icon = Icons.Default.Info,
                    text = "Báo cáo lỗi",
                    onClick = {
                        onReportError(booking)
                        onDismiss()
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Cancellation policy option
                MenuOption(
                    icon = Icons.Default.Favorite/*Policy*/,
                    text = "Chính sách hủy đặt phòng",
                    onClick = {
                        onCancellationPolicy(booking)
                        onDismiss()
                    }
                )
            }
        }
    }
}

@Composable
private fun MenuOption(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}
