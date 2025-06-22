package com.example.mainapp.screens.hoteldetail.components

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
fun HotelDescriptionSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Header với thanh màu cam bên trái
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
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
                text = "Giới thiệu",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Text(
            text = "NNT HOTEL đa năng cấp hạng phòng, thay đổi nội thất để hoàn thiện tốt mang lại trải nghiệm tốt hơn cho quý khách. Quý khách được miễn phí:",
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "• SmartTivi kết nối mạng",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "• Bãi đỗ xe máy, xe oto rộng miễn phí",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Quý khách có thể thanh toán bằng thẻ tín dụng, chuyển khoản, momo ... tại khách sạn sau khi thuê phòng",
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "NNT HOTEL",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Địa chỉ: Số 43 ngõ 6 Trần Quốc Hoàn, Cầu Giấy, Hà Nội",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        Text(
            text = "Phone: 024 37545966",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        Text(
            text = "Hotline zalo: 034 9881105",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}
