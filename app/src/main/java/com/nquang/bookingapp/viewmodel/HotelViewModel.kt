package com.nquang.bookingapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nquang.bookingapp.model.FavouriteHotelModel
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingModelGet
import com.nquang.bookingapp.model.RoomBookingStatus
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.utils.Utils
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class HotelViewModel : ViewModel() {
    val hotelModels = mutableStateListOf<HotelModelGet?>()
    val roomModels = mutableStateListOf<RoomModelGet?>()
    val roomBookingModels = mutableStateListOf<RoomBookingModelGet?>()
    val favouriteHotelModels = mutableStateListOf<FavouriteHotelModel?>()
    var newRoomBookingModel = mutableStateOf<RoomBookingModelGet?>(null)

    init {
        fetchHotelData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchHotelData() {
        viewModelScope.launch {
            try {
                val hotelList: List<HotelModelGet> = FirebaseUtils.findAllHotel()
                for (hotel in hotelList) {
                    Log.d("HotelViewModel", "Hotel: $hotel")
                }
                hotelModels.addAll(hotelList)
                Log.d("HotelViewModel", "Number of hotel: ${hotelList.size}")

                val roomBookingList: List<RoomBookingModelGet> = FirebaseUtils.findAllRoomBooking()
                for (roomBooking in roomBookingList) {
                    Log.d("HotelViewModel", "Room Booking: $roomBooking")
                }
                roomBookingModels.addAll(roomBookingList)
                Log.d("HotelViewModel", "Number of room booking: ${roomBookingList.size}")

                val roomList: List<RoomModelGet> = FirebaseUtils.findAllRoom()
                for (room in roomList) {
                    Log.d("HotelViewModel", "Room: $room")
                }
                roomModels.addAll(roomList)
                Log.d("HotelViewModel", "Number of room: ${roomList.size}")

                val favouriteHotelList: List<FavouriteHotelModel> =
                    FirebaseUtils.findAllFavouriteHotel()
                for (favouriteHotel in favouriteHotelList) {
                    Log.d("HotelViewModel", "Favourite Hotel: $favouriteHotel")
                }
                favouriteHotelModels.addAll(favouriteHotelList)
                Log.d("HotelViewModel", "Number of favourite hotel: ${favouriteHotelList.size}")
            } catch (e: Exception) {
                Log.e("HotelViewModel", "Error fetching user data: ${e.message}")
            }
        }
    }

    fun refreshUserData() {
        fetchHotelData()
    }

    fun updateRoomIdNewRoomBookingModel(roomId: String) {
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(roomId = roomId)
    }

    fun updateCheckInDateTimeNewRoomBookingModelWithString(checkInDateTime: String) {
        val testLcd: LocalDateTime = Utils.stringToLocalDateTimeWithTime(checkInDateTime)!!
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkInDateTime = testLcd
        )
    }

    fun updateCheckOutDateTimeNewRoomBookingModelWithString(checkOutDateTime: String) {
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkOutDateTime = Utils.stringToLocalDateTimeWithTime(checkOutDateTime)!!
        )
    }

    fun updateCheckInDateTimeNewRoomBookingModel(checkInDateTime: LocalDateTime) {
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkInDateTime = checkInDateTime
        )
    }

    fun updateCheckOutDateTimeNewRoomBookingModel(checkOutDateTime: LocalDateTime) {
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkOutDateTime = checkOutDateTime
        )
    }

    fun updateStatusNewRoomBookingModel(status: String) {
        newRoomBookingModel.value =
            newRoomBookingModel.value?.copy(status = RoomBookingStatus.valueOf(status))
    }

    fun updateTypeNewRoomBookingModel(type: String) {
        newRoomBookingModel.value =
            newRoomBookingModel.value?.copy(type = RoomBookingType.fromValue(type)!!)
    }

    fun updateTimeCheckInDateTimeNewRoomBookingModel(time: String) {
        val newStringDateTime = Utils.localDateTimeToString(newRoomBookingModel.value!!.checkInDateTime) + " - " + time
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkInDateTime = Utils.stringToLocalDateTimeWithTime(newStringDateTime)!!
        )
    }

    fun updateTimeCheckOutDateTimeNewRoomBookingModel(time: String) {
        val newStringDateTime = Utils.localDateTimeToString(newRoomBookingModel.value!!.checkOutDateTime) + " - " + time
        newRoomBookingModel.value = newRoomBookingModel.value?.copy(
            checkOutDateTime = Utils.stringToLocalDateTimeWithTime(newStringDateTime)!!
        )
    }

}