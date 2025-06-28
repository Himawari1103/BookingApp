package com.example.mainapp.screens.tourism

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mainapp.screens.tourism.components.TourismHeader
import com.example.mainapp.screens.tourism.components.DestinationSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourismScreen(
    navController: NavController
) {
    var selectedDestination by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header với background đẹp
        TourismHeader(
            onBackClick = { navController.popBackStack() }
        )

        // Form tìm kiếm
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Bạn đang tìm gì?",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Chỉ có phần chọn điểm đến
                DestinationSelector(
                    selectedDestination = selectedDestination,
                    onDestinationSelected = { destination ->
                        selectedDestination = destination
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Nút khám phá với màu cam
                Button(
                    onClick = {
                        // Navigate to tourism detail screen
                        if (selectedDestination.isNotEmpty()) {
                            navController.navigate("tourism_detail/$selectedDestination")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5722) // Màu cam giống các phần khác
                    )
                ) {
                    Text(
                        text = "Khám phá",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}
