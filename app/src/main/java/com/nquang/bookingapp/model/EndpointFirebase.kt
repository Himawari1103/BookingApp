package com.nquang.bookingapp.model

enum class EndpointFirebase(val value: String) {
    USERS("users"),
    HOTELS("hotels"),
    ROOMS("rooms"),
    ROOM_BOOKINGS("roomBookings"),
    FAVOURITE_HOTELS("favouriteHotels")
}