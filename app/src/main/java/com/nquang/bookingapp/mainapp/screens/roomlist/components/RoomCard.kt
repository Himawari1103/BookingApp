package com.nquang.bookingapp.mainapp.screens.roomlist.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nquang.bookingapp.mainapp.data.model.roomlist.Room
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.viewmodel.HotelViewModel

/**
 * Card hiển thị thông tin phòng trong danh sách
 * Bao gồm gallery ảnh, thông tin cơ bản, giá và các nút action
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoomCard(
    room: RoomModelGet,
    onBookClick: () -> Unit,
    onDetailClick: () -> Unit,
    hotelViewModel: HotelViewModel,
    hotelModel: HotelModelGet,
    canBooking: Boolean,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Phần gallery ảnh phòng
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                val pagerState = rememberPagerState(pageCount = { room.thumbnailImages?.size ?: 0 })

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    AsyncImage(
                        model = room.thumbnailImages?.get(page),
                        contentDescription = "Ảnh phòng ${page + 1}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Chỉ số trang (dots)
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(room.thumbnailImages?.size ?: 0) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pagerState.currentPage == index) Color.White
                                    else Color.White.copy(alpha = 0.5f)
                                )
                        )
                    }
                }
            }

            // Phần thông tin phòng
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Tên phòng
                Text(
                    text = room.id + room.type.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tiện ích phòng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    hotelModel.amenities?.forEachIndexed { index, amenity ->
                        Text(
                            text = amenity,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        if (index < hotelModel.amenities.size - 1 && index < 2) {
                            Text(
                                text = " • ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dòng giá và nút đặt phòng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${room.price}đ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Button(
                        onClick = onBookClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B35)
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.height(40.dp),
                        enabled = canBooking
                    ) {
                        Text(
                            text = "Đặt phòng",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Thông tin quyền lợi
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Phương thức thanh toán
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Thanh toán",
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Tất cả phương thức thanh toán",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    // Hủy phòng miễn phí
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Shield,
                            contentDescription = "Hủy phòng",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Miễn phí hủy phòng trong 24h",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    // Đảm bảo giá
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.MonetizationOn,
                            contentDescription = "Đảm bảo giá",
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Hoàn tiền nếu giá tại khách sạn rẻ hơn. ",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Divider(color = Color(0xFFE0E0E0))

                Spacer(modifier = Modifier.height(12.dp))

                // Dòng cuối: chính sách và chi tiết phòng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "Chính sách",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Chính sách hủy phòng",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    // Nút chi tiết phòng - làm nổi bật hơn và tăng vùng click
                    Button(
                        onClick = {
                            println("DEBUG: Bấm chi tiết phòng, roomId = ${room.id}")
                            onDetailClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color(0xFFFF6B35)
                        ),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Text(
                            text = "Chi tiết phòng",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Xem chi tiết",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
