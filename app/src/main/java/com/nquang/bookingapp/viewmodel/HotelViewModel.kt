package com.nquang.bookingapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.utils.FirebaseUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class HotelViewModel : ViewModel() {
    val hotelModels = mutableStateListOf<HotelModelGet?>()
//    val roomModels = mutableStateListOf<RoomModelGet?>()

    init {
        fetchHotelData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchHotelData() {
        viewModelScope.launch {
            try {
                val hotelList : List<HotelModelGet> = FirebaseUtils.findAllHotel()
                for (hotel in hotelList) {
                    Log.d("HotelViewModel", "Hotel: $hotel")
                }
                hotelModels.addAll(hotelList)
                Log.d("HotelViewModel", "Number of hotel: ${hotelList.size}")
            } catch (e: Exception) {
                Log.e("HotelViewModel", "Error fetching user data: ${e.message}")
            }
        }
    }

    fun refreshUserData() {
        fetchHotelData()
    }

}