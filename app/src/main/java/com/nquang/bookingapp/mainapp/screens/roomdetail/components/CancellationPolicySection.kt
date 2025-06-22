package com.example.travelapp.screens.hoteldetails.components

import androidx.compose.foundation.background
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

@Composable
fun CancellationPolicySection() {
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
                text = "Chính sách hủy phòng",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Nội dung chính sách chính
        Text(
            text = "Hủy miễn phí trước 16:30, 05/06/2025 đối với tất cả các phương thức thanh toán.",
            fontSize = 14.sp,
            color = Color.Black,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Phần lưu ý
        Text(
            text = "Lưu ý:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Bullet point 1
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "• ",
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = "Có thể được hủy miễn phí trong vòng 5 phút kể từ thời điểm đặt phòng thành công nếu thời gian yêu cầu hủy không được quá giờ nhận phòng.",
                fontSize = 14.sp,
                color = Color.Black,
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
        }

        // Bullet point 2
        Row(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "• ",
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = "Không thể hủy phòng khi sử dụng ưu đãi không hoàn hủy.",
                fontSize = 14.sp,
                color = Color.Black,
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}
