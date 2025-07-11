package com.nquang.bookingapp.mainapp.screens.roomlist.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.repository.RoomRepository
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingStatus
import com.nquang.bookingapp.viewmodel.HotelViewModel
import java.time.LocalDateTime

/**
 * Nội dung danh sách phòng
 * Hiển thị tất cả các phòng có sẵn
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomListContent(
    navController: NavController,
    hotelModel: HotelModelGet,
    hotelViewModel: HotelViewModel
) {
    val rooms = hotelModel.roomList

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Hiển thị từng phòng
        val roomBookingList = hotelViewModel.roomBookingModels
        val roomBookingListOfThisHotel = roomBookingList.filter { roomBookingOfThisHotel ->
            var check = false
            for (roomBooking in hotelModel.roomList) {
                if (roomBooking.id == roomBookingOfThisHotel?.roomId) {
                    check = true
                    break;
                }
            }
            check
        }

        val currentType = hotelViewModel.newRoomBookingModel.value?.type
        val currentCheckIn = hotelViewModel.newRoomBookingModel.value?.checkInDateTime
        val currentCheckOut = hotelViewModel.newRoomBookingModel.value?.checkOutDateTime

        rooms.forEach { room ->
            var canBooking = true;
            for (roomBooking in roomBookingListOfThisHotel) {
                if (room.id == roomBooking?.roomId) {
                    if (roomBooking.status == RoomBookingStatus.PENDING) {
                        if (currentType == roomBooking.type) {
                            if (currentCheckIn!!.dayOfMonth >= roomBooking.checkInDateTime.dayOfMonth && currentCheckOut!!.dayOfMonth <= roomBooking.checkOutDateTime.dayOfMonth) {
                                canBooking = false
                                break;
                            }
                        }
                    }
                }
            }
            if(!currentCheckIn!!.isAfter(LocalDateTime.now())) canBooking = false
            RoomCard(
                room = room,
                onBookClick = {
                    // Chuyển đến màn hình thanh toán
                    println("DEBUG: Đặt phòng ${room.id}, chuyển đến thanh toán")
                    hotelViewModel.updateRoomIdNewRoomBookingModel(room.id)
                    navController.navigate("payment/${hotelModel.id}")
                },
                onDetailClick = {
                    // Chuyển đến chi tiết phòng
                    println("DEBUG: Chuẩn bị chuyển đến chi tiết phòng ${room.id}")
                    navController.navigate("room_detail/${room.id}")
                },
                hotelViewModel = hotelViewModel,
                hotelModel = hotelModel,
                canBooking = canBooking
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Khoảng trống ở cuối
        Spacer(modifier = Modifier.height(80.dp))
    }
}
