package com.example.mainapp.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mainapp.data.model.DestinationItem
import com.example.mainapp.R


@Composable
fun WhereToGoSection(navController: NavController) {
    val destinations = listOf(
        DestinationItem("Bangkok", "Tiếp tục khám phá", R.drawable.hotel2),
        DestinationItem("TP Hồ Chí Minh", "Tiếp tục khám phá", R.drawable.hotel3),
        DestinationItem("Hà Nội", "Khám phá thủ đô", R.drawable.hotel4),
        DestinationItem("Đà Nẵng", "Thành phố đáng sống", R.drawable.hotel5)
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bạn muốn đi đâu chơi?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Xem thêm",
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(destinations) { destination ->
                DestinationCard(
                    destination = destination,
                    onClick = {
                        navController.navigate("hotel_detail/1") // Fixed route format - using hotel ID instead of destination name
                    }
                )
            }
        }
    }
}

@Composable
fun DestinationCard(destination: DestinationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = destination.subtitle,
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = destination.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
