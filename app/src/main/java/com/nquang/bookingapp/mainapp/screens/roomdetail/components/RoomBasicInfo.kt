package com.example.mainapp.screens.roomdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.roomlist.Room

/**
 * Thông tin cơ bản của phòng
 * Hiển thị tên phòng và các đặc điểm chính
 */
@Composable
fun RoomBasicInfo(room: Room) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Tên phòng
        Text(
            text = room.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Đặc điểm phòng (giường đôi, diện tích, cửa sổ)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            room.amenities.forEachIndexed { index, amenity ->
                Text(
                    text = amenity,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                // Thêm dấu • giữa các amenities
                if (index < room.amenities.size - 1) {
                    Text(
                        text = " • ",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // Đường phân cách ở cuối phần
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp),
            color = Color(0xFFD0D0D0),
            thickness = 2.dp
        )
    }
}
