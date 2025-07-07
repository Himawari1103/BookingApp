package com.example.mainapp.screens.hotellist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.repository.HotelListRepository
import com.example.mainapp.screens.hotellist.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelListScreen(navController: NavController, destination: String = "Việt Nam") {
    var searchQuery by remember { mutableStateOf("") }
    var showFilterDialog by remember { mutableStateOf(false) }
    
    val hotels = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            HotelListRepository.getAllHotels()
        } else {
            HotelListRepository.searchHotels(searchQuery)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 120.dp, bottom = 16.dp)
        ) {
            // Filter section
            item {
                HotelFilterSection(
                    resultCount = hotels.size,
                    onFilterClick = { showFilterDialog = true }
                )
            }
            
            // Hotel cards
            items(hotels) { hotel ->
                HotelCard(
                    hotel = hotel,
                    onHotelClick = { 
                        navController.navigate("hotel_detail/${hotel.id}")
                    },
                    onFavoriteClick = { 
                        // Handle favorite toggle
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        // Sticky header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(10f),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                )
                
                HotelListHeader(
                    destination = destination,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
