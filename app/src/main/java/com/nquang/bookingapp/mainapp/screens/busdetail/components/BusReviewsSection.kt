package com.example.mainapp.screens.busdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.busdetail.BusReview

@Composable
fun BusReviewsSection(
    rating: Float,
    reviewCount: Int,
    reviews: List<BusReview>,
    onViewAllReviews: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Đánh giá",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Overall Rating
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rating.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "/5",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                // Stars
                Row {
                    repeat(5) { index ->
                        Text(
                            text = if (index < rating.toInt()) "★" else "☆",
                            color = if (index < rating.toInt()) Color(0xFFFFC107) else Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
                Text(
                    text = "$reviewCount Đánh giá",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        // Recent Reviews
        reviews.take(2).forEach { review ->
            ReviewCard(review = review)
        }

        // View All Button
        OutlinedButton(
            onClick = onViewAllReviews,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Đọc tất cả đánh giá",
                color = Color(0xFF2196F3)
            )
        }
    }
}

@Composable
private fun ReviewCard(review: BusReview) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.userName.first().toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = review.userName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = review.date,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Rating Stars
            Row {
                repeat(5) { index ->
                    Text(
                        text = if (index < review.rating) "★" else "☆",
                        color = if (index < review.rating) Color(0xFFFFC107) else Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            // Comment
            Text(
                text = review.comment,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }
    }
}
