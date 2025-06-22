package com.example.mainapp.screens.transportation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mainapp.data.model.transportation.TransportType
import com.example.mainapp.data.repository.TransportationRepository
import com.example.mainapp.screens.transportation.components.TransportationHeader
import com.example.mainapp.screens.transportation.components.TransportCategoryTabs
import com.example.mainapp.screens.transportation.components.TransportServiceCard

@Composable
fun TransportationScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf(TransportType.BUS) }
    val repository = remember { TransportationRepository() }
    val categories = remember { repository.getTransportCategories() }
    val services by remember(selectedCategory) {
        derivedStateOf { repository.getServicesByType(selectedCategory) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Header
        TransportationHeader(
            onBackClick = { navController.popBackStack() }
        )

        // Category Tabs
        TransportCategoryTabs(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        // Services List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section Title
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
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Services Grid
            items(services.chunked(2)) { rowServices ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowServices.forEach { service ->
                        TransportServiceCard(
                            service = service,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                // Navigate to bus detail
                                navController.navigate("bus_detail/${service.id}")
                            }
                        )
                    }

                    // Fill remaining space if odd number of items
                    if (rowServices.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            // Bottom padding
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
