package com.nquang.bookingapp.model

data class FavouriteHotelModel( // n-n user-hotel
    val id: String,
    val userId:String,
    val hotelId:String,
)