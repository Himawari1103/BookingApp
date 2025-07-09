package com.nquang.bookingapp.mainapp.screens.payment.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.payment.PaymentMethod
import com.nquang.bookingapp.mainapp.data.model.payment.PaymentSummary
import com.nquang.bookingapp.R
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailsSection(
    selectedPaymentMethod: PaymentMethod?,
    onSelectPaymentMethod: () -> Unit,
    paymentSummary:String
) {


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Header với thanh màu cam bên trái
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            // Thanh màu cam bên trái
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
                text = paymentSummary + "đ",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

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
                text = paymentSummary + "đ",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Payment method selection
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelectPaymentMethod() }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hiển thị icon của phương thức thanh toán
                if (selectedPaymentMethod == null) {
                    // Icon ic_payment khi chưa chọn phương thức thanh toán
                    Icon(
                        painter = painterResource(id = R.drawable.ic_payment),
                        contentDescription = "Payment",
                        tint = Color.Unspecified, // Giữ màu gốc của icon
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    // Hiển thị icon của phương thức thanh toán đã chọn
                    Icon(
                        painter = painterResource(id = selectedPaymentMethod.icon),
                        contentDescription = selectedPaymentMethod.name,
                        tint = Color.Unspecified, // Giữ màu gốc của icon
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = selectedPaymentMethod?.name ?: "Chọn phương thức thanh toán",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }

            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Select",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }

        // Divider
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp),
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )
    }
}
