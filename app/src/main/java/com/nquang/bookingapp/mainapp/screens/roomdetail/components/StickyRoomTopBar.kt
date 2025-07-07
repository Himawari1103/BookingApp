package com.nquang.bookingapp.mainapp.screens.roomdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Sticky top bar cho màn hình chi tiết phòng
 * Hiển thị khi cuộn, có tên phòng ở giữa
 */
@Composable
fun StickyRoomTopBar(
    navController: NavController,
    roomName: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Phủ status bar
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .background(Color.White)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Nội dung chính của thanh sticky
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút quay lại
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Transparent, CircleShape)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                // Tên phòng ở giữa
                Text(
                    text = roomName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                
                // Nhóm nút bên phải
                Row {
                    IconButton(
                        onClick = { /* Xử lý yêu thích */ },
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.Transparent, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Yêu thích",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    IconButton(
                        onClick = { /* Xử lý chia sẻ */ },
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.Transparent, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Chia sẻ",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
