package com.nquang.bookingapp.model

data class RoomModelGet(
    val id: String,
    val hotelId: String,
    val type: RoomType,
    val price: Int,
    val discount: Float = 0F,
    val thumbnailImages: List<String>? = null, // url to images
    val bookingList: List<RoomBookingModelGet>? = null,
)

data class RoomModelSet(
    val id: String,
    val hotelId: String,
    val type: RoomType,
    val price: Int,
    val discount: Float = 0F,
    val thumbnailImages: List<String>? = null, // url to images
    val bookingList: List<String>? = null, // list IdBookingModel
)

enum class RoomType {
    SINGLE,
    DOUBLE,
    FAMILY,
    VIP
}
//    TRIPLE,
//    QUEEN,
//    KING,



