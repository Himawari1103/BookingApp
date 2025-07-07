package com.nquang.bookingapp.mainapp.screens.bookingdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.booking.BookingItem
import com.nquang.bookingapp.mainapp.data.model.booking.BookingStatus
import androidx.compose.foundation.BorderStroke

@Composable
fun CancellationPolicySection(
    booking: BookingItem,
    onCancelBooking: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Header với thanh màu cam bên trái
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
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
                text = "Chính sách hủy phòng",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        when (booking.status) {
            BookingStatus.WAITING_CHECKIN -> {
                // Active booking - show cancellation policy and button
                Text(
                    text = "Hủy miễn phí trước 07:00, 09/02/2026",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                val annotatedText = buildAnnotatedString {
                    append("Tôi đồng ý với ")
                    withStyle(style = SpanStyle(color = Color(0xFFFF6B35))) {
                        append("Điều khoản và Chính sách")
                    }
                    append(" đặt phòng.")
                }

                Text(
                    text = annotatedText,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Dịch vụ hỗ trợ khách hàng - Liên hệ ngay",
                    fontSize = 14.sp,
                    color = Color(0xFFFF6B35),
                    modifier = Modifier.clickable { }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Cancel booking button
                OutlinedButton(
                    onClick = onCancelBooking,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFF6B35),
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFF6B35)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Hủy đặt phòng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            BookingStatus.CANCELLED -> {
                // Cancelled booking - show cancellation info
                Text(
                    text = "Đặt phòng đã được hủy thành công.",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Thời gian hủy: 08/02/2026 15:30",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            BookingStatus.COMPLETED -> {
                // Completed booking - show completion info
                Text(
                    text = "Đặt phòng đã hoàn thành thành công.",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
