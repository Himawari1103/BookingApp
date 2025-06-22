package com.example.mainapp.screens.roomdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Thanh đặt phòng sticky ở dưới cùng màn hình
 * Hiển thị thông tin đặt phòng và nút đặt phòng
 */
@Composable
fun RoomBookingBottomBar(
    price: String,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Thông tin thời gian đặt phòng
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFFFF4E6), // Màu cam nhạt
                        RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Schedule,
                    contentDescription = "Thời gian",
                    tint = Color(0xFFFF6B35),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "02 giờ | 17:30 → 19:30, 05/06",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Giá và nút đặt phòng
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hiển thị giá
                Text(
                    text = "${price}đ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Nút đặt phòng
                Button(
                    onClick = onBookClick,
                    modifier = Modifier
                        .height(48.dp)
                        .width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B35)
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "Đặt phòng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }

            // Thêm khoảng cách với viền dưới điện thoại
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}
