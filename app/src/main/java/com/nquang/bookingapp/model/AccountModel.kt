package com.nquang.bookingapp.model

data class AccountModelGet(
    val id: String, // = userId
    val bookingList: List<RoomBookingModelGet>?,
    val favouriteHotelList: List<FavouriteHotelModel>?,
)

data class AccountModelSet(
    val id: String, // = userId
    val bookingList: List<String>? = null, // list IdBookingModel
    val favouriteHotelList: List<String>? = null, // list IdFavouriteHotelModel
)