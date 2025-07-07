package com.nquang.bookingapp.mainapp.screens.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.payment.PaymentMethod
import com.nquang.bookingapp.R

@Composable
fun PaymentMethodScreen(
    onBackClick: () -> Unit,
    onPaymentMethodSelected: (PaymentMethod) -> Unit
) {
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }

    val paymentMethods = listOf(
        PaymentMethod("momo", "Ví MoMo", R.drawable.ic_momo),
        PaymentMethod("zalopay", "Ví ZaloPay", R.drawable.ic_zalopay, "Nhập mã G2JZALOPAY để được giảm giá cho đơn từ 150K"),
        PaymentMethod("shopeepay", "Ví ShopeePay", R.drawable.ic_shopeepay, "Ưu đãi ShopeePay giảm ngay 5.000đ"),
        PaymentMethod("credit", "Thẻ Credit", R.drawable.ic_credit_card),
        PaymentMethod("atm", "Thẻ ATM", R.drawable.ic_atm_card),
        PaymentMethod("hotel_payment", "Trả tại khách sạn", R.drawable.ic_hotel, isEnabled = true)
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }

                    Text(
                        text = "Phương thức thanh toán",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.size(40.dp))
                }
            }
        }

        // Payment methods list
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(paymentMethods) { method ->
                PaymentMethodItem(
                    method = method,
                    isSelected = selectedMethod?.id == method.id,
                    onSelect = { selectedMethod = method }
                )
            }
        }

        // Confirm button
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Button(
                onClick = {
                    selectedMethod?.let { onPaymentMethodSelected(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedMethod != null) Color(0xFFFF6B35) else Color.Gray
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = selectedMethod != null
            ) {
                Text(
                    text = "Xác nhận",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PaymentMethodItem(
    method: PaymentMethod,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = method.isEnabled) { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFFF4E6) else Color.White
        ),
        border = if (isSelected) {
            BorderStroke(2.dp, Color(0xFFFF6B35))
        } else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hiển thị icon của phương thức thanh toán từ drawable resources
            Icon(
                painter = painterResource(id = method.icon),
                contentDescription = method.name,
                modifier = Modifier.size(32.dp),
                tint = if (method.isEnabled) Color.Unspecified else Color.Gray
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = method.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (method.isEnabled) Color.Black else Color.Gray
                )

                method.description?.let { desc ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = desc,
                        fontSize = 12.sp,
                        color = Color(0xFF2196F3)
                    )
                }
            }

            RadioButton(
                selected = isSelected,
                onClick = { if (method.isEnabled) onSelect() },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFFFF6B35)
                ),
                enabled = method.isEnabled
            )
        }
    }
}
