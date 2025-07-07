package com.nquang.bookingapp.mainapp.screens.hoteldetails.components

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

@Composable
fun StickyHotelTopBar(
    navController: NavController,
    hotelName: String,
    modifier: Modifier = Modifier
) {
    // Surface phủ toàn bộ phần trên bao gồm cả status bar
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp // Tạo đổ bóng để thanh nổi bật
    ) {
        // Column chứa toàn bộ nội dung
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Phần phủ cho status bar (không có nội dung, chỉ để tạo nền)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding() // Chiều cao bằng status bar
                    .background(Color.White) // Nền trắng phủ status bar
            )

            // Khoảng cách nhỏ giữa status bar và nội dung chính
            Spacer(modifier = Modifier.height(8.dp))

            // Row chứa các thành phần chính của thanh sticky
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp) // Chiều cao cố định cho thanh sticky
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Căn đều 2 đầu
                verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
            ) {
                // Nút Back ở bên trái
                IconButton(
                    onClick = { navController.popBackStack() }, // Quay lại màn hình trước
                    modifier = Modifier
                        .size(36.dp) // Kích thước icon button
                        .background(Color.Transparent, CircleShape)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp) // Kích thước icon
                    )
                }

                // Tên khách sạn ở giữa
                Text(
                    text = hotelName,
                    fontSize = 16.sp, // Font size phù hợp
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    maxLines = 1, // Chỉ hiển thị 1 dòng
                    overflow = TextOverflow.Ellipsis, // Thêm "..." nếu text quá dài
                    modifier = Modifier.weight(1f) // Chiếm hết không gian còn lại
                )

                // Nhóm nút action ở bên phải
                Row {
                    // Nút yêu thích
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(36.dp) // Kích thước icon button
                            .background(Color.Transparent, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp) // Kích thước icon
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp)) // Khoảng cách giữa 2 nút

                    // Nút chia sẻ
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(36.dp) // Kích thước icon button
                            .background(Color.Transparent, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp) // Kích thước icon
                        )
                    }
                }
            }
        }
    }
}
