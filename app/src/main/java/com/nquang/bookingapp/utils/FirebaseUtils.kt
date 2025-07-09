package com.nquang.bookingapp.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.nquang.bookingapp.model.FavouriteHotelModel
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.HotelPolicies
import com.nquang.bookingapp.model.HotelReview
import com.nquang.bookingapp.model.RoomBookingModelGet
import com.nquang.bookingapp.model.RoomBookingModelSet
import com.nquang.bookingapp.model.RoomBookingStatus
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.model.RoomType
import com.nquang.bookingapp.model.UserModel
import com.nquang.bookingapp.model.EndpointFirebase
import kotlinx.coroutines.tasks.await
import java.time.LocalTime

class FirebaseUtils {
    companion object {
        val database = FirebaseDatabase.getInstance()

        suspend fun findUserByEmail(email: String): UserModel? {
            return try {
                val query = database.reference.child("users")
                    .orderByChild("email")
                    .equalTo(email)
                    .get()
                    .await()
                query.children.firstOrNull()?.let { snapshot ->
//                    val uid = snapshot.key ?: ""
//                    val email = snapshot.child("email").getValue(String::class.java) ?: ""
//                    val fullName = snapshot.child("fullName").getValue(String::class.java) ?: ""
                    val user = snapshot.getValue(UserModel::class.java)
                    Log.d(
                        "FindUserByEmail Debug",
                        "Found user with UID: ${user!!.uid}, email: $email, fullName: ${user.fullName}"
                    )
//                    UserModel(fullName = fullName, email = email, uid = uid)
                    user
                }
            } catch (e: Exception) {
                Log.e("FindUserByEmail Error", "Error finding user by email: ${e.message}")
                null
            }
        }

        suspend fun findUserByUid(uid: String): UserModel? {
            return try {
                val query = database.reference.child("users")
                    .orderByKey()
                    .equalTo(uid)
                    .get()
                    .await()
                query.children.firstOrNull()?.let { snapshot ->
                    val user = snapshot.getValue(UserModel::class.java)
                    Log.d("FindUserByUid Debug", "Found user: $user")
                    user
                }


            } catch (e: Exception) {
                Log.e("FindUserByUid Error", "Error finding user by uid: ${e.message}")
                null
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findAllHotel(): List<HotelModelGet> {
            return try {
                val query = database.reference.child("hotels")
                    .get()
                    .await()
                val hotelList: List<HotelModelGet> = query.children.mapNotNull { snapshot ->
                    Log.d("FindAllHotel Debug", "Found hotel key: ${snapshot.key}")
                    val id = snapshot.key ?: ""
                    val name = snapshot.child("name").getValue(String::class.java) ?: ""
                    val phoneNumber = snapshot.child("phoneNumber").getValue(String::class.java)
                        ?: ""
                    val rating = snapshot.child("rating").getValue(Float::class.java) ?: 0f
                    val reviewCount = snapshot.child("reviewCount").getValue(Int::class.java) ?: 0
                    val address = snapshot.child("address").getValue(String::class.java) ?: ""
                    val longitude = snapshot.child("longitude").getValue(Double::class.java) ?: 0.0
                    val latitude = snapshot.child("latitude").getValue(Double::class.java) ?: 0.0
                    val thumbnailImages = snapshot.child("thumbnailImages")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})
                    val amenities = snapshot.child("amenities")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})
                    val description = snapshot.child("description").getValue(String::class.java)
                        ?: ""
                    val policies = HotelPolicies(
                        checkIn = Utils.stringToLocalTime(
                            snapshot.child("policies").child("checkIn")
                                .getValue(String::class.java)
                        ) ?: LocalTime.now(),
                        checkOut = Utils.stringToLocalTime(
                            snapshot.child("policies").child("checkOut")
                                .getValue(String::class.java)
                        ) ?: LocalTime.now(),
                        otherPolicy = snapshot.child("policies").child("otherPolicy")
                            .getValue(String::class.java) ?: ""
                    )
                    val reviews = snapshot.child("reviews")
                        .getValue(object : GenericTypeIndicator<List<HotelReview>>() {})
                    val roomList = mutableListOf<RoomModelGet>();
                    val roomIdList = snapshot.child("roomList")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})
                    if (roomIdList != null) {
                        for (roomId in roomIdList) {
                            val room = findRoomById(roomId)
                            if (room != null) {
                                roomList.add(room)
                            }
                        }
                    }
                    HotelModelGet(
                        id = id,
                        name = name,
                        phoneNumber = phoneNumber,
                        rating = rating,
                        reviewCount = reviewCount,
                        address = address,
                        longitude = longitude,
                        latitude = latitude,
                        thumbnailImages = thumbnailImages,
                        amenities = amenities,
                        description = description,
                        policies = policies,
                        reviews = reviews,
                        roomList = roomList
                    )
                }
                Log.d("FindAllHotel Debug", "Found ${hotelList.size} hotels")
                hotelList
            } catch (e: Exception) {
                Log.e("FindAllHotel Error", "Error finding all hotels: ${e.message}")
                emptyList()
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findRoomById(id: String): RoomModelGet? {
            return try {
                val query = database.reference.child("rooms")
                    .orderByKey()
                    .equalTo(id)
                    .get()
                    .await()
                query.children.firstOrNull()?.let { snapshot ->
                    val id = snapshot.key
                    val hotelId = snapshot.child("hotelId").getValue(String::class.java)
                    val type =
                        RoomType.valueOf(snapshot.child("type").getValue(String::class.java)!!)
                    val price = snapshot.child("price").getValue(Int::class.java)
                    val discount = snapshot.child("discount").getValue(Float::class.java)
                    val thumbnailImages = snapshot.child("thumbnailImages")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})
                    val bookingIdList = snapshot.child("bookingList")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})

                    val bookingList = mutableListOf<RoomBookingModelGet>()
                    if (bookingIdList != null) {
                        for (bookingId in bookingIdList) {
                            val booking = findRoomBookingById(bookingId)
                            if (booking != null) {
                                bookingList.add(booking)
                                Log.d("FindRoomById Debug", "Found booking: $booking")
                            }
                        }
                    }
                    val room = RoomModelGet(
                        id!!,
                        hotelId!!,
                        type,
                        price!!,
                        discount!!,
                        thumbnailImages,
                        bookingList
                    )
                    Log.d("FindUserByUid Debug", "Found room: $room")
                    room
                }
            } catch (e: Exception) {
                Log.e("FindRoomById Error", "Error finding room by id: ${e.message}")
                null
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findRoomBookingById(id: String): RoomBookingModelGet? {
            return try {
                val query = database.reference.child("roomBookings")
                    .orderByKey()
                    .equalTo(id)
                    .get()
                    .await()
                query.children.firstOrNull()?.let { snapshot ->
                    val id = snapshot.key ?: ""
                    val userId = snapshot.child("userId").getValue(String::class.java)
                    val roomId = snapshot.child("roomId").getValue(String::class.java)
                    val checkInDate = Utils.stringToLocalDateTime(
                        snapshot.child("checkInDateTime").getValue(String::class.java)
                    )
                    val checkOutDate = Utils.stringToLocalDateTime(
                        snapshot.child("checkOutDateTime").getValue(String::class.java)
                    )
                    val status = RoomBookingStatus.valueOf(
                        snapshot.child("status").getValue(String::class.java)!!
                    )
                    val type = RoomBookingType.valueOf(
                        snapshot.child("type").getValue(String::class.java)!!
                    )
                    val roomBooking = RoomBookingModelGet(
                        id,
                        userId!!,
                        roomId!!,
                        checkInDate!!,
                        checkOutDate!!,
                        status,
                        type
                    )
                    Log.d("FindUserByUid Debug", "Found room: $roomBooking")
                    roomBooking
                }
            } catch (e: Exception) {
                Log.e("FindRoomById Error", "Error finding room by id: ${e.message}")
                null
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findAllRoomBooking(): List<RoomBookingModelGet> {
            return try {
                val query = database.reference.child(EndpointFirebase.ROOM_BOOKINGS.value)
                    .get()
                    .await()
                val roomBooking: List<RoomBookingModelGet> = query.children.mapNotNull { snapshot ->
                    Log.d("FindRoomBooking Debug", "Found roomBooking key: ${snapshot.key}")
                    val id = snapshot.key ?: ""
                    val userId = snapshot.child("userId").getValue(String::class.java)
                    val roomId = snapshot.child("roomId").getValue(String::class.java)
                    val checkInDate = Utils.stringToLocalDateTimeWithTime(
                        snapshot.child("checkInDateTime").getValue(String::class.java)
                    )
                    val checkOutDate = Utils.stringToLocalDateTimeWithTime(
                        snapshot.child("checkOutDateTime").getValue(String::class.java)
                    )
                    val status = RoomBookingStatus.valueOf(
                        snapshot.child("status").getValue(String::class.java)!!
                    )
                    val type = RoomBookingType.valueOf(
                        snapshot.child("type").getValue(String::class.java)!!
                    )
                    RoomBookingModelGet(
                        id,
                        userId!!,
                        roomId!!,
                        checkInDate!!,
                        checkOutDate!!,
                        status,
                        type
                    )
                }
                Log.d("FindAllRoomBooking Debug", "Found ${roomBooking.size} roomBookings")
                roomBooking
            } catch (e: Exception) {
                Log.e("FindAllRoomBooking Error", "Error finding all hotels: ${e.message}")
                emptyList()
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findAllRoom(): List<RoomModelGet> {
            return try {
                val query = database.reference.child(EndpointFirebase.ROOMS.value)
                    .get()
                    .await()
                val roomList: List<RoomModelGet> = query.children.mapNotNull { snapshot ->
                    Log.d("FindAllHotel Debug", "Found hotel key: ${snapshot.key}")
                    val id = snapshot.key ?: ""
                    val hotelId = snapshot.child("hotelId").getValue(String::class.java)
                    val type =
                        RoomType.valueOf(snapshot.child("type").getValue(String::class.java)!!)
                    val price = snapshot.child("price").getValue(Int::class.java)
                    val discount = snapshot.child("discount").getValue(Float::class.java)
                    val thumbnailImages = snapshot.child("thumbnailImages")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})
                    val bookingIdList = snapshot.child("bookingList")
                        .getValue(object : GenericTypeIndicator<List<String>>() {})

                    val bookingList = mutableListOf<RoomBookingModelGet>()
                    if (bookingIdList != null) {
                        for (bookingId in bookingIdList) {
                            val booking = findRoomBookingById(bookingId)
                            if (booking != null) {
                                bookingList.add(booking)
                                Log.d("FindRoomById Debug", "Found booking: $booking")
                            }
                        }
                    }

                    RoomModelGet(
                        id,
                        hotelId!!,
                        type,
                        price!!,
                        discount!!,
                        thumbnailImages,
                        bookingList
                    )
                }
                Log.d("FindAllRoom Debug", "Found ${roomList.size} rooms")
                roomList
            } catch (e: Exception) {
                Log.e("FindAllRoom Error", "Error finding all rooms: ${e.message}")
                emptyList()
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun findAllFavouriteHotel(): List<FavouriteHotelModel> {
            return try {
                val query = database.reference.child(EndpointFirebase.FAVOURITE_HOTELS.value)
                    .get()
                    .await()
                val favouriteHotelList: List<FavouriteHotelModel> =
                    query.children.mapNotNull { snapshot ->
                        Log.d(
                            "FindFavouriteHotel Debug",
                            "Found favouriteHotel key: ${snapshot.key}"
                        )
                        val id = snapshot.key ?: ""
                        val userId = snapshot.child("userId").getValue(String::class.java)
                        val hotelId = snapshot.child("hotelId").getValue(String::class.java)

                        FavouriteHotelModel(
                            id = id,
                            userId = userId!!,
                            hotelId = hotelId!!,
                        )
                    }
                Log.d("FindAllRoomBooking Debug", "Found ${favouriteHotelList.size} roomBookings")
                favouriteHotelList
            } catch (e: Exception) {
                Log.e("FindAllRoomBooking Error", "Error finding all hotels: ${e.message}")
                emptyList()
            }
        }

        fun saveUserdata(userModel: UserModel) {
            //chèn dữ liệu vào database
            database.getReference().child("users").child(userModel.uid).setValue(userModel)
                .addOnSuccessListener {
                    Log.d("saveUserdata", "Success")
                }
                .addOnFailureListener { exception ->
                    Log.d("saveUserdata", "Failure", exception)
                }
        }

        fun saveRoomBooking(roomBookingModel: RoomBookingModelSet) {
            database.getReference().child("roomBookings").child(roomBookingModel.id)
                .setValue(roomBookingModel)
                .addOnSuccessListener {
                    Log.d("saveRoomBooking", "Success")
                }
                .addOnFailureListener { exception ->
                    Log.d("saveRoomBooking", "Failure", exception)
                }
        }

        fun saveFavouriteHotel(favouriteHotelModel: FavouriteHotelModel) {
            database.getReference().child("favouriteHotels").child(favouriteHotelModel.id)
                .setValue(favouriteHotelModel)
                .addOnSuccessListener {
                    Log.d("saveFavouriteHotel", "Success")
                }
                .addOnFailureListener { exception ->
                    Log.d("saveFavouriteHotel", "Failure", exception)
                }
        }

        fun removeRoomBookingById(id: String) {
            database.getReference().child(EndpointFirebase.ROOM_BOOKINGS.value).child(id)
                .removeValue()
                .addOnSuccessListener {
                    Log.d("removeRoomBookingById", "Success")
                }
                .addOnFailureListener { exception ->
                    Log.d("removeRoomBookingById", "Failure", exception)
                }
        }

        suspend fun removeFavouriteHotelByUserIdAndHotelId(userId: String, hotelId: String){

            val query = database.reference.child(EndpointFirebase.FAVOURITE_HOTELS.value)
                .orderByChild("userId")
                .equalTo(userId)
                .get()
                .await()
            query.children.firstOrNull()?.let { snapshot ->
//                    val uid = snapshot.key ?: ""
//                    val email = snapshot.child("email").getValue(String::class.java) ?: ""
//                    val fullName = snapshot.child("fullName").getValue(String::class.java) ?: ""
                val favouriteHotelModel = snapshot.getValue(FavouriteHotelModel::class.java)

                if (favouriteHotelModel != null && favouriteHotelModel.hotelId == hotelId) {
                    database.getReference().child(EndpointFirebase.FAVOURITE_HOTELS.value)
                        .child(favouriteHotelModel.id)
                        .removeValue()
                        .addOnSuccessListener {
                            Log.d("removeRoomBookingById", "Success")
                        }
                        .addOnFailureListener { exception ->
                            Log.d("removeRoomBookingById", "Failure", exception)
                        }
                }

            }
        }


    }
}