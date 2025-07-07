package com.nquang.bookingapp.mainapp.screens.hoteldetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.model.common.Hotel
import com.nquang.bookingapp.mainapp.data.model.reviews.ReviewItem
import com.nquang.bookingapp.mainapp.data.repository.ReviewRepository

@Composable
fun ReviewsSection(hotel: Hotel, navController: NavController) {
    // Lấy data từ repository
    val reviews = ReviewRepository.getReviewsForHotelDetail(hotel.id)

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
                text = "Đánh giá",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Rating overview
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Điểm số lớn
            Text(
                text = "${hotel.rating}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "/5",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 2.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                // Hàng sao
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < hotel.rating.toInt()) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Số lượng đánh giá
                Text(
                    text = "${hotel.reviewCount} Đánh giá",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Danh sách đánh giá có thể lướt ngang
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(reviews) { review ->
                ReviewCard(review = review)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Nút đọc tất cả đánh giá
        OutlinedButton(
            onClick = {
                navController.navigate("reviews/${hotel.id}")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Đọc tất cả đánh giá",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        // Divider cuối phần
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
fun ReviewCard(review: ReviewItem) {
    Card(
        modifier = Modifier.width(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header với avatar và thông tin user
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            when (review.userName.first().uppercaseChar()) {
                                'H' -> Color(0xFF4CAF50)
                                'K' -> Color(0xFF2196F3)
                                'M' -> Color(0xFF9C27B0)
                                'T' -> Color(0xFFFF9800)
                                else -> Color(0xFF607D8B)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.userName.first().uppercase(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = review.userName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    // Room type nếu có
                    review.roomType?.let { roomType ->
                        Text(
                            text = roomType,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    // Ngày đánh giá
                    Text(
                        text = review.date,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Rating stars
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                repeat(5) { index ->
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < review.rating) Color(0xFFFFC107) else Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Comment
            Text(
                text = review.comment,
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
