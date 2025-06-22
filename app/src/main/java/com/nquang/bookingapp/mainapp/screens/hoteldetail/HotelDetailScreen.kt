package com.example.mainapp.screens.hoteldetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.repository.HotelRepository
import com.example.mainapp.screens.hoteldetail.components.CancellationPolicySection
import com.example.mainapp.screens.hoteldetail.components.CheckInOutSection
import com.example.mainapp.screens.hoteldetail.components.HotelAmenitiesSection
import com.example.mainapp.screens.hoteldetail.components.HotelDescriptionSection
import com.example.mainapp.screens.hoteldetail.components.HotelImageGallery
import com.example.mainapp.screens.hoteldetail.components.HotelInfoSection
import com.example.mainapp.screens.hoteldetails.components.HotelTopBar
import com.example.mainapp.screens.hoteldetails.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(navController: NavController, hotelId: String) {
    val hotel = HotelRepository.getHotelById(hotelId)
    val scrollState = rememberScrollState()

    // Theo dõi khi nào cần hiển thị sticky header
    val showStickyHeader by remember {
        derivedStateOf {
            scrollState.value > 500
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Gallery ảnh khách sạn
            HotelImageGallery(hotel)

            // Chi tiết khách sạn với spacing nhất quán
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Phần thông tin khách sạn (tên, địa chỉ, rating)
                HotelInfoSection(hotel)

                // Phần đánh giá
                ReviewsSection(hotel, navController)

                // Phần tiện ích khách sạn
                HotelAmenitiesSection()

                // Phần mô tả khách sạn
                HotelDescriptionSection()

                // Phần thời gian check-in/check-out
                CheckInOutSection()

                // Phần chính sách hủy phòng
                CancellationPolicySection()

                // Thêm khoảng trống cho nút đặt phòng ở dưới
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        // Top bar thường (khi chưa cuộn) - với padding cho status bar
        if (!showStickyHeader) {
            HotelTopBar(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(top = 8.dp)
                    .zIndex(10f)
            )
        }

        // Sticky top bar với tên khách sạn (khi cuộn qua phần thông tin)
        if (showStickyHeader) {
            StickyHotelTopBar(
                navController = navController,
                hotelName = hotel.name,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(10f)
            )
        }

        // Nút đặt phòng ở dưới cùng
        HotelBookingButton(
            price = hotel.price,
            originalPrice = hotel.originalPrice,
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            hotelId = hotelId
        )
    }
}
