package com.nquang.bookingapp.mainapp.screens.roomdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.model.roomlist.Room
import com.nquang.bookingapp.mainapp.data.repository.RoomRepository
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.CancellationPolicySection
import com.nquang.bookingapp.mainapp.screens.roomdetail.components.*

/**
 * Màn hình chi tiết phòng - hiển thị thông tin đầy đủ về một phòng cụ thể
 * Bao gồm: ảnh phòng, thông tin cơ bản, quyền lợi, bảng giá, chính sách
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailScreen(
    navController: NavController, roomId: String,
) {
    println("DEBUG: RoomDetailScreen được gọi với roomId = $roomId")

    // Hiển thị thông báo đang ở màn hình chi tiết phòng
    LaunchedEffect(Unit) {
        println("DEBUG: Đã vào màn hình chi tiết phòng với ID = $roomId")
    }

    // Lấy thông tin phòng từ repository và xử lý lỗi
    val room = remember(roomId) {
        try {
            RoomRepository.getRoomById(roomId)
        } catch (e: Exception) {
            println("ERROR: Lỗi khi lấy thông tin phòng - ${e.message}")
            e.printStackTrace()
            null
        }
    }

    // Hiển thị màn hình lỗi nếu không lấy được thông tin phòng
    if (room == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Không thể tải thông tin phòng",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("Quay lại")
            }
        }
        return
    }

    println("DEBUG: Đã lấy được thông tin phòng ${room.name}")

    val scrollState = rememberScrollState()

    // Theo dõi trạng thái cuộn để hiển thị sticky header
    val showStickyHeader by remember {
        derivedStateOf {
            scrollState.value > 300 // Hiển thị khi cuộn qua 300px
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Nội dung chính có thể cuộn
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Gallery ảnh phòng - sử dụng biến state để kiểm soát lỗi
            val galleryError = remember { mutableStateOf(false) }

            if (!galleryError.value) {
                // Sử dụng LaunchedEffect để xử lý lỗi thay vì try-catch trực tiếp
                RoomImageGalleryWrapper(room = room, onError = { galleryError.value = true })
            } else {
                // Hiển thị placeholder nếu có lỗi
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Không thể tải ảnh phòng")
                }
            }

            // Thông tin chi tiết phòng với khoảng cách rõ ràng
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                // Thông tin cơ bản của phòng
                RoomBasicInfo(room = room)

                // Quyền lợi khi đặt phòng
                RoomBenefitsSection()

                // Bảng giá phòng theo thời gian
                RoomPricingSection()

                // Giờ nhận và trả phòng
                CheckInOutTimesSection()

                // Chính sách hủy phòng
                CancellationPolicySection()

                // Tăng khoảng trống cho thanh đặt phòng ở dưới để tránh dính
                Spacer(modifier = Modifier.height(120.dp))
            }
        }

        // Header thường khi chưa cuộn (với padding cho status bar)
        if (!showStickyHeader) {
            RoomDetailTopBar(
                navController = navController,
                roomName = room.name,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(top = 8.dp)
                    .zIndex(10f)
            )
        }

        // Sticky header khi cuộn (với tên phòng)
        if (showStickyHeader) {
            StickyRoomTopBar(
                navController = navController,
                roomName = room.name,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(10f)
            )
        }

        // Thanh đặt phòng sticky ở dưới cùng
        RoomBookingBottomBar(
            price = room.price,
            onBookClick = {
                // Chuyển đến màn hình thanh toán
                println("DEBUG: Đặt phòng ${room.name}, chuyển đến thanh toán")
                navController.navigate("payment/${room.id}")
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Wrapper cho RoomImageGallery để xử lý lỗi mà không cần try-catch trong Composable
 */
@Composable
fun RoomImageGalleryWrapper(room: Room, onError: () -> Unit) {
    // Sử dụng LaunchedEffect để xử lý lỗi
    LaunchedEffect(room) {
        try {
            // Chỉ kiểm tra xem có thể truy cập ảnh không
            if (room.images.isEmpty()) {
                throw Exception("Không có ảnh")
            }
        } catch (e: Exception) {
            println("ERROR: Lỗi khi hiển thị gallery - ${e.message}")
            onError()
        }
    }

    // Hiển thị gallery nếu không có lỗi
    RoomImageGallery(room = room)
}
