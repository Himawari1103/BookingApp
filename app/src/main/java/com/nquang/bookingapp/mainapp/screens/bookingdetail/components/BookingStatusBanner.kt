package com.example.mainapp.screens.bookingdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.booking.BookingStatus

@Composable
fun BookingStatusBanner(status: BookingStatus) {
    val (backgroundGradient, iconBackground, statusIcon, overlayIcon, title, subtitle) = when (status) {
        BookingStatus.WAITING_CHECKIN -> StatusConfig(
            backgroundGradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
            ),
            iconBackground = Color(0xFF2196F3),
            statusIcon = Icons.Default.Business,
            overlayIcon = Icons.Default.Schedule,
            title = "Chờ nhận phòng",
            subtitle = "Hoàn tất đặt phòng! Đừng quên đến nhận phòng đúng giờ nhé."
        )
        BookingStatus.COMPLETED -> StatusConfig(
            backgroundGradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFFE8F5E8), Color(0xFFC8E6C9))
            ),
            iconBackground = Color(0xFF4CAF50),
            statusIcon = Icons.Default.Business,
            overlayIcon = Icons.Default.CheckCircle,
            title = "Hoàn thành",
            subtitle = "Đặt phòng của bạn đã hoàn thành, hy vọng bạn đã có một trải nghiệm tuyệt vời!"
        )
        BookingStatus.CANCELLED -> StatusConfig(
            backgroundGradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFFFFEBEE), Color(0xFFFFCDD2))
            ),
            iconBackground = Color(0xFFF44336),
            statusIcon = Icons.Default.Business,
            overlayIcon = Icons.Default.Cancel,
            title = "Đã hủy",
            subtitle = "Đã hủy đặt phòng vào 09:00, 08/01/2025."
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Remove vertical padding to control spacing externally
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundGradient, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon container with status overlay
                Box(
                    modifier = Modifier.size(56.dp)
                ) {
                    // Main hotel icon
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(iconBackground.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = statusIcon,
                            contentDescription = title,
                            tint = iconBackground,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // Status overlay icon
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = overlayIcon,
                            contentDescription = "Status",
                            tint = iconBackground,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text content
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = iconBackground
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

private data class StatusConfig(
    val backgroundGradient: Brush,
    val iconBackground: Color,
    val statusIcon: ImageVector,
    val overlayIcon: ImageVector,
    val title: String,
    val subtitle: String
)
