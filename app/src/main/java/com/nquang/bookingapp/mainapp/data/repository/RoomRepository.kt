package com.example.mainapp.data.repository

import com.example.mainapp.R
import com.example.mainapp.data.model.roomlist.Room

object RoomRepository {

    fun getRooms(): List<Room> {
        return listOf(
            Room(
                id = "standard",
                name = "STANDARD ROOM 1",
                roomNumbers = "201, 204, 301, 304, 401",
                bedType = "Giường đôi",
                amenities = listOf("Wi-Fi miễn phí", "Lễ tân 24/24", "Cửa sổ thông thoáng"),
                price = "349.000",
                originalPrice = 600000,
                discount = 42,
                rewardPoints = 3490,
                images = listOf(
                    R.drawable.hotel1,
                    R.drawable.hotel2,
                    R.drawable.hotel3,
                    R.drawable.hotel4
                )
            ),
            Room(
                id = "vip",
                name = "VIP ROOM 2",
                roomNumbers = "203, 205, 303, 305, 403, 405",
                bedType = "Giường đôi",
                amenities = listOf("Wi-Fi miễn phí", "Lễ tân 24/24", "Cửa sổ thông thoáng"),
                price = "549.000",
                originalPrice = 800000,
                discount = 31,
                rewardPoints = 5490,
                images = listOf(
                    R.drawable.hotel4,
                    R.drawable.hotel3,
                    R.drawable.hotel2,
                    R.drawable.hotel1
                )
            )
        )
    }

    fun getRoomById(roomId: String): Room {
        return getRooms().find { it.id == roomId } ?: getRooms().first()
    }
}
