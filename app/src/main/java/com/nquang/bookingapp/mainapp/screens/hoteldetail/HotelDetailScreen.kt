package com.nquang.bookingapp.mainapp.screens.hoteldetail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.nquang.bookingapp.mainapp.data.repository.HotelRepository
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.CancellationPolicySection
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.CheckInOutSection
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.HotelAmenitiesSection
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.HotelDescriptionSection
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.HotelImageGallery
import com.nquang.bookingapp.mainapp.screens.hoteldetail.components.HotelInfoSection
import com.nquang.bookingapp.mainapp.screens.hoteldetails.components.HotelTopBar
import com.nquang.bookingapp.mainapp.screens.hoteldetails.components.*
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(
    navController: NavController,
    hotelId: String,
    hotelViewModel: HotelViewModel,
) {
    Log.d("HotelDetailScreen", "Hotel ID: $hotelId")
    var hotelModel: HotelModelGet? = null
    for (hotel in hotelViewModel.hotelModels) {
        if(hotel!!.id == hotelId){
            hotelModel = hotel
            break;
        }
    }
    hotelModel!!

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
            HotelImageGallery(hotelModel)

            // Chi tiết khách sạn với spacing nhất quán
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Phần thông tin khách sạn (tên, địa chỉ, rating)
                HotelInfoSection(hotelModel)

                // Phần đánh giá
                ReviewsSection(hotelModel, navController)

                // Phần tiện ích khách sạn
                HotelAmenitiesSection(hotelModel)

                // Phần mô tả khách sạn
                HotelDescriptionSection(hotelModel)

                // Phần thời gian check-in/check-out
                CheckInOutSection(hotelModel)

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
                hotelName = hotelModel.name,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(10f)
            )
        }

        // Nút đặt phòng ở dưới cùng
        HotelBookingButton(
            price = hotelModel.roomList[0].price.toString(),
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            hotelId = hotelId
        )
    }
}
