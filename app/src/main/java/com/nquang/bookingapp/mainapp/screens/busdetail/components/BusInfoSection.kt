package com.nquang.bookingapp.mainapp.screens.busdetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.busdetail.BusServiceDetail

@Composable
fun BusInfoSection(
    busService: BusServiceDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Service name
        Text(
            text = busService.name,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Route
        Text(
            text = "Khởi hành từ ${busService.route}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF2196F3),
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Rating and reviews
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFA726),
                modifier = Modifier.size(20.dp)
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            Text(
                text = "${busService.rating}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = "(${busService.reviewCount} Đánh giá) • ${busService.reviewCount}+ Đã đặt",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Location
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
            
            Spacer(modifier = Modifier.width(4.dp))
            
            Text(
                text = busService.location,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
