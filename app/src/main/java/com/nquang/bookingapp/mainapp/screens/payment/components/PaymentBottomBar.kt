package com.example.mainapp.screens.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.R
import com.example.mainapp.data.model.payment.PaymentMethod

@Composable
fun PaymentBottomBar(
    totalAmount: String,
    selectedPaymentMethod: PaymentMethod?,
    onPaymentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Payment method selection if selected
            selectedPaymentMethod?.let { method ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Hiển thị icon của phương thức thanh toán đã chọn
                        Icon(
                            painter = painterResource(id = method.icon),
                            contentDescription = method.name,
                            tint = Color.Unspecified, // Giữ màu gốc của icon
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = method.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }

                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "Change",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Total and payment button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Tổng thanh toán",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "${totalAmount}đ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Button(
                    onClick = onPaymentClick,
                    modifier = Modifier
                        .height(48.dp)
                        .width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B35)
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = if (selectedPaymentMethod != null) "Thanh toán" else "Đặt phòng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }

            // Thêm khoảng cách với viền dưới điện thoại
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}
