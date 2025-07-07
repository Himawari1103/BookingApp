package com.nquang.bookingapp.mainapp.screens.transportation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.model.transportation.TransportType
import com.nquang.bookingapp.mainapp.data.repository.TransportationRepository
import com.nquang.bookingapp.mainapp.screens.transportation.components.TransportCategoryTabs
import com.nquang.bookingapp.mainapp.screens.transportation.components.TransportServiceCard
import com.nquang.bookingapp.R

@Composable
fun TransportationScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf(TransportType.BUS) }
    val repository = remember { TransportationRepository() }
    val categories = remember { repository.getTransportCategories() }
    val services by remember(selectedCategory) {
        derivedStateOf { repository.getServicesByType(selectedCategory) }
    }

    val backgroundImage = when (selectedCategory) {
        TransportType.BUS -> R.drawable.xebuyt
        TransportType.TRAIN -> R.drawable.tauhoa
        TransportType.CAR_RENTAL -> R.drawable.thuexe
        TransportType.AIRPORT_SHUTTLE -> R.drawable.xesanbay
        TransportType.FLIGHT -> R.drawable.chuyenbay
        TransportType.PRIVATE_CAR -> R.drawable.thuexerieng
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header with background image covering both title and tabs
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
        ) {
            // Background Image with rounded corners
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(id = backgroundImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.6f)
                                )
                            )
                        )
                )
            }

            // Content over background
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(20.dp)
            ) {
                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.size(44.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    IconButton(
                        onClick = { /* Handle share */ },
                        modifier = Modifier.size(44.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Title
                Text(
                    text = "Di chuyển",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 32.sp
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Category Tabs over background
                TransportCategoryTabs(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )
            }
        }

        // Services List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = when (selectedCategory) {
                        TransportType.BUS -> "Tất cả xe buýt"
                        TransportType.TRAIN -> "Tất cả tàu hỏa"
                        TransportType.CAR_RENTAL -> "Tất cả thuê xe"
                        TransportType.AIRPORT_SHUTTLE -> "Tất cả xe sân bay"
                        TransportType.FLIGHT -> "Tất cả chuyến bay"
                        TransportType.PRIVATE_CAR -> "Tất cả xe riêng"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(services.chunked(2)) { rowServices ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowServices.forEach { service ->
                        TransportServiceCard(
                            service = service,
                            onClick = {
                                navController.navigate("bus_detail/${service.id}")
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (rowServices.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
