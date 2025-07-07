package com.nquang.bookingapp.mainapp.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.model.ServiceItem
import com.nquang.bookingapp.mainapp.data.model.ServiceType
import com.nquang.bookingapp.R

@Composable
fun FeaturedServicesSection(navController: NavController) {
    val services = listOf(
        ServiceItem(
            id = "1",
            title = "Vietnam e-Visa Service for International Tourist",
            location = "Thành phố Hồ Chí Minh",
            rating = 4.7f,
            reviewCount = 213,
            price = "1,115,000",
            type = ServiceType.VISA,
            imageRes = R.drawable.hotel6
        ),
        ServiceItem(
            id = "2",
            title = "Halong Bay Day Tour: Sung Sot Cave by Luxury Iris Cruise",
            location = "Nhiều điểm khởi hành",
            rating = 5.0f,
            reviewCount = 845,
            price = "1,384,600",
            type = ServiceType.TOUR,
            imageRes = R.drawable.halong
        ),
        ServiceItem(
            id = "3",
            title = "Vé Xem Phim CGV 2D tại Việt Nam",
            location = "Thành phố Hồ Chí Minh",
            rating = 4.5f,
            reviewCount = 300,
            price = "110,000",
            type = ServiceType.ENTERTAINMENT,
            badge = "300+ Đã được đặt",
            imageRes = R.drawable.hotel7
        ),
        ServiceItem(
            id = "4",
            title = "Tour Ngày Tham Quan Vịnh Hạ Long bằng Du Thuyền 5 Sao Cozy Bay",
            location = "Nhiều điểm khởi hành",
            rating = 4.8f,
            reviewCount = 156,
            price = "2,500,000",
            type = ServiceType.TOUR,
            imageRes = R.drawable.hotel8
        ),
        ServiceItem(
            id = "5",
            title = "Vé Thủy Cung Lotte World Hà Nội",
            location = "Cách đây 10.9km",
            rating = 4.4f,
            reviewCount = 301,
            price = "450,000",
            type = ServiceType.ENTERTAINMENT,
            imageRes = R.drawable.hotel9
        ),
        ServiceItem(
            id = "6",
            title = "Khám Phá Phiên Bản Tuyệt Nhất Của Bạn",
            location = "Toàn quốc",
            rating = 4.9f,
            reviewCount = 89,
            price = "Free",
            type = ServiceType.EXPERIENCE,
            badge = "Bắt đầu ngay!",
            imageRes = R.drawable.hotel10
        )
    )

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(services) { service ->
            ServiceCard(
                service = service,
                onClick = {
                    navController.navigate("hotel_detail/${if (service.id.toIntOrNull() != null) service.id else "1"}") // Fixed route format
                }
            )
        }
    }
}

@Composable
fun ServiceCard(service: ServiceItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                Image(
                    painter = painterResource(id = service.imageRes),
                    contentDescription = service.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                service.badge?.let { badge ->
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        color = Color(0xFFFF6B35),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = badge,
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = service.location,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = service.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${service.rating}(${service.reviewCount})",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (service.price == "Free") "Miễn phí" else "Từ đ ${service.price}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (service.price == "Free") Color(0xFF00C851) else Color.Black
                )
            }
        }
    }
}
