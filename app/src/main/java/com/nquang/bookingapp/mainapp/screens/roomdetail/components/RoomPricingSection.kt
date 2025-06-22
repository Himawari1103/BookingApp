package com.example.mainapp.screens.roomdetail.components

import androidx.compose.foundation.background
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

@Composable
fun RoomPricingSection() {
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
                    .height(20.dp)
                    .background(
                        Color(0xFFFF6B35),
                        RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Giá phòng",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Danh sách các gói giá
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Gói 2 giờ đầu
            PricingItem(
                icon = Icons.Default.Schedule,
                title = "02 giờ đầu",
                price = "250.000đ",
                iconColor = Color(0xFFFF6B35)
            )

            // Gói 1 giờ thêm (thụt lề)
            PricingItem(
                icon = Icons.Default.AccessTime,
                title = "01 giờ thêm",
                price = "50.000đ",
                iconColor = Color(0xFF666666),
                isSubItem = true
            )

            // Đường phân cách nhỏ
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )

            // Gói qua đêm
            PricingItem(
                icon = Icons.Default.Bedtime,
                title = "01 đêm",
                price = "400.000đ",
                iconColor = Color(0xFF9C27B0)
            )

            // Đường phân cách nhỏ
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )

            // Gói cả ngày
            PricingItem(
                icon = Icons.Default.CalendarToday,
                title = "01 ngày",
                price = "750.000đ",
                iconColor = Color(0xFF2196F3)
            )
        }

        // Đường phân cách chính ở cuối phần
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp),
            color = Color(0xFFD0D0D0),
            thickness = 1.dp
        )
    }
}

@Composable
fun PricingItem(
    icon: ImageVector,
    title: String,
    price: String,
    iconColor: Color,
    isSubItem: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (isSubItem) 24.dp else 0.dp
            )
            .padding(vertical = 6.dp), // Tách padding vertical ra riêng
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Phần bên trái: icon và tên gói
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = title,
                fontSize = if (isSubItem) 14.sp else 15.sp,
                fontWeight = if (isSubItem) FontWeight.Normal else FontWeight.Medium,
                color = if (isSubItem) Color.Gray else Color.Black
            )
        }

        // Phần bên phải: giá
        Text(
            text = price,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
