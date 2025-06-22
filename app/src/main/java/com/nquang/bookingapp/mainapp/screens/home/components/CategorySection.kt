package com.example.mainapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mainapp.data.model.CategoryItem

@Composable
fun CategorySection(navController: NavController) {
    val categories = listOf(
        CategoryItem("Du lịch", Icons.Default.Tour, Color(0xFFE91E63), "entertainment"), // Pink
        CategoryItem("Khách sạn", Icons.Default.Hotel, Color(0xFFFFC107), "hotels"), // Amber
        CategoryItem("Di chuyển", Icons.Default.DirectionsBus, Color(0xFF2196F3), "transport"), // Blue
        CategoryItem("Chuyến bay", Icons.Default.Flight, Color(0xFF9C27B0), "flight"), // Purple
        CategoryItem("Tất cả các\nmục", Icons.Default.Apps, Color(0xFF4CAF50), "all_categories") // Green
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            categories.forEach { category ->
                CategoryCard(
                    category = category,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        when (category.id) {
                            "transport" -> navController.navigate("transportation")
                            "hotels" -> navController.navigate("hotel_detail/1")
                            "flight" -> {
                                // Navigate to flight booking screen when implemented
                            }
                            "entertainment" -> {
                                // Navigate to tourism/entertainment screen when implemented
                            }
                            "all_categories" -> {
                                // Navigate to all categories screen when implemented
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryCard(category: CategoryItem, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(category.color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                category.icon,
                contentDescription = category.title,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.title,
            fontSize = 11.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(65.dp)
        )
    }
}
