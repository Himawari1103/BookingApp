package com.example.mainapp.screens.bookingdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.booking.BookingItem

@Composable
fun HotelSelectionSection(
    booking: BookingItem,
    onHotelClick: () -> Unit
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
                text = "Lựa chọn của bạn",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onHotelClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hotel image
            Image(
                painter = painterResource(id = booking.hotelImage),
                contentDescription = "Hotel image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Hotel info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = booking.hotelName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = booking.roomType.split(" | ").getOrNull(1) ?: booking.roomType,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "17A, ngách 1 Ngõ 612 - Lac Long Quan, Nhật Tân, Tây Hồ, Hà Nội, Vietnam",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }

            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Go to hotel",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp),
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )
    }
}
