package com.nquang.bookingapp.mainapp.screens.bookings.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nquang.bookingapp.mainapp.data.model.booking.BookingItem
import com.nquang.bookingapp.model.RoomBookingModelGet
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.R
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingCard(
    booking: RoomBookingModelGet,
    onMenuClick: (RoomBookingModelGet) -> Unit,
    onCardClick: (RoomBookingModelGet) -> Unit,
    hotelViewModel: HotelViewModel
) {
    val roomModel = hotelViewModel.roomModels.find { it?.id == booking.roomId }
    val hotelModel = hotelViewModel.hotelModels.find { it?.id == roomModel?.hotelId }

    roomModel!!
    hotelModel!!

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick(booking) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header với ngày giờ và status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = Utils.localDateTimeToString(booking.checkInDateTime)!!,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${if (booking.checkInDateTime.hour < 10) "0" else ""}${booking.checkInDateTime.hour}:${if (booking.checkInDateTime.minute < 10) "0" else ""}${booking.checkInDateTime.minute}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Status badge
                    Surface(
                        color = booking.status.color.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = booking.status.value,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = booking.status.color,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Menu button
                    IconButton(
                        onClick = { onMenuClick(booking) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Booking info
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Hotel image
//                Image(
//                    painter = painterResource(id = R.drawable.hotel1),
//                    contentDescription = "Hotel image",
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    contentScale = ContentScale.Crop
//                )

                AsyncImage(
                    model = hotelModel.thumbnailImages?.get(0),
                    contentDescription = "Hotel image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Booking details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Mã đặt phòng: ${booking.id}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = hotelModel.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = booking.type.value + "|" + roomModel.hotelId + roomModel.type,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    var paymentSummary: Int = 0;
                    paymentSummary = when (booking.type) {
                        RoomBookingType.ONLY_DAY -> {
                            roomModel.price / 20 * 8 / 1000 * 1000
                        }

                        RoomBookingType.ONLY_NIGHT -> {
                            roomModel.price / 20 * 11 / 1000 * 1000
                        }

                        RoomBookingType.FULL_DAY -> {
                            roomModel.price * (booking.checkOutDateTime.dayOfMonth - booking.checkInDateTime.dayOfMonth)
                        }
                    }

                    // Price with payment method icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        Icon(
//                            painter = painterResource(id = booking.paymentMethod.icon),
//                            contentDescription = booking.paymentMethod.displayName,
//                            tint = Color.Unspecified,
//                            modifier = Modifier.size(16.dp)
//                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = paymentSummary.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF6B35)
                        )
                    }
                }
            }
        }
    }
}
