package com.nquang.bookingapp.mainapp.screens.roomlist.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.repository.RoomRepository

/**
 * Nội dung danh sách phòng
 * Hiển thị tất cả các phòng có sẵn
 */
@Composable
fun RoomListContent(navController: NavController) {
    val rooms = RoomRepository.getRooms()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Hiển thị từng phòng
        rooms.forEach { room ->
            RoomCard(
                room = room,
                onBookClick = {
                    // Chuyển đến màn hình thanh toán
                    println("DEBUG: Đặt phòng ${room.name}, chuyển đến thanh toán")
                    navController.navigate("payment/${room.id}")
                },
                onDetailClick = {
                    // Chuyển đến chi tiết phòng
                    println("DEBUG: Chuẩn bị chuyển đến chi tiết phòng ${room.id}")
                    navController.navigate("room_detail/${room.id}")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Khoảng trống ở cuối
        Spacer(modifier = Modifier.height(80.dp))
    }
}
