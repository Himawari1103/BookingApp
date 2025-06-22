package com.example.mainapp.screens.roomdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Top bar thường cho màn hình chi tiết phòng
 * Hiển thị khi chưa cuộn, có nền trong suốt
 */
@Composable
fun RoomDetailTopBar(
    navController: NavController,
    roomName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Nút quay lại
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.9f), CircleShape)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Quay lại",
                tint = Color.Black
            )
        }
        
        // Nhóm nút bên phải
        Row {
            IconButton(
                onClick = { /* Xử lý yêu thích */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Yêu thích",
                    tint = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            IconButton(
                onClick = { /* Xử lý chia sẻ */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
            ) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "Chia sẻ",
                    tint = Color.Black
                )
            }
        }
    }
}
