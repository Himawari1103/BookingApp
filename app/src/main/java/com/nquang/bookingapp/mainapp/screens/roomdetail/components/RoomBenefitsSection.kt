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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoomBenefitsSection() {
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
                text = "Quyền lợi đặt phòng",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Danh sách quyền lợi
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Quyền lợi 1: Thanh toán linh hoạt
            BenefitItem(
                icon = Icons.Default.CheckCircle,
                text = "Tất cả phương thức thanh toán",
                iconColor = Color(0xFF4CAF50)
            )

            // Quyền lợi 2: Hủy phòng miễn phí
            BenefitItem(
                icon = Icons.Default.Shield,
                text = "Miễn phí hủy phòng trong 24h",
                iconColor = Color(0xFF2196F3)
            )

            // Quyền lợi 3: Đảm bảo giá tốt nhất
            BenefitItem(
                icon = Icons.Default.MonetizationOn,
                text = "Hoàn tiền nếu tìm được giá rẻ hơn",
                iconColor = Color(0xFFFF9800)
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
fun BenefitItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    iconColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}
