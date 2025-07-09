package com.nquang.bookingapp.mainapp.screens.bookingdetail.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailsSection(booking: RoomBookingModelGet, hotelViewModel: HotelViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val roomModel = hotelViewModel.roomModels.find { it?.id == booking.roomId }
        val hotelModel = hotelViewModel.hotelModels.find { it?.id == roomModel?.hotelId }
        roomModel!!
        hotelModel!!

        // Header với thanh màu cam bên trái
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(24.dp)
                    .background(
                        Color(0xFFFF6B35),
                        RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Chi tiết thanh toán",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Payment status
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trạng thái",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Thanh toán trực tiếp",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(8.dp))

                AsyncImage(
                    model = hotelModel?.thumbnailImages!![0],
                    contentDescription = "Hotel image",
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

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
        // Room price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tiền phòng",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = paymentSummary.toString(),
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total amount
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tổng thanh toán",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = paymentSummary.toString(),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp),
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )
    }
}
