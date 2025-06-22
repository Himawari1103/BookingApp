package com.example.mainapp.screens.busdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.*

@Composable
fun BusBookingBottomBar(
    price: Int,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Price
            Column {
                Text(
                    text = formatPrice(price),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
                Text(
                    text = "/ người",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            
            // Book button
            Button(
                onClick = onBookClick,
                modifier = Modifier
                    .height(48.dp)
                    .widthIn(min = 120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B35)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Đặt vé xe",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

private fun formatPrice(price: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    return "đ ${formatter.format(price)}"
}
