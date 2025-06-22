package com.example.mainapp.screens.reviews.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.model.reviews.ReviewData

@Composable
fun ReviewCard(review: ReviewData) {
    var isExpanded by remember { mutableStateOf(false) }
    val maxLines = if (isExpanded) Int.MAX_VALUE else 6
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // User info and rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            when (review.userName.first().uppercaseChar()) {
                                'M' -> Color(0xFF4CAF50)
                                'T' -> Color(0xFF2196F3)
                                'V' -> Color(0xFF9C27B0)
                                'L' -> Color(0xFFFF9800)
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
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.userName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    
                    Text(
                        text = review.date,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Rating stars and satisfaction text
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < review.rating) Color(0xFFFFC107) else Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                // Satisfaction text based on rating
                when {
                    review.rating >= 5f -> {
                        Text(
                            text = "Rất hài lòng",
                            fontSize = 14.sp,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    review.rating >= 4f -> {
                        Text(
                            text = "Hài lòng",
                            fontSize = 14.sp,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            
            // Room type if available
            review.roomType?.let { roomType ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Đánh giá cho: $roomType",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Review text
            Text(
                text = review.comment,
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
            
            // Show more/less button for long reviews
            if (review.comment.length > 200) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isExpanded) "Thu gọn" else "Xem thêm",
                    fontSize = 14.sp,
                    color = Color(0xFFFF6B35),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                )
            }
            
            // Review images
            if (review.images.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Hiển thị đánh giá gốc (Nội dung trên đã được dịch)",
                    fontSize = 12.sp,
                    color = Color(0xFFFF6B35),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.height(
                        when {
                            review.images.size <= 3 -> 80.dp
                            review.images.size <= 6 -> 160.dp
                            else -> 160.dp
                        }
                    ),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(review.images.take(6)) { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}
