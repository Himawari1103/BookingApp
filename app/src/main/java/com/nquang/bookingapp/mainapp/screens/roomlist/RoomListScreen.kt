package com.nquang.bookingapp.mainapp.screens.roomlist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.data.repository.HotelRepository
import com.nquang.bookingapp.mainapp.screens.roomlist.components.*
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomListScreen(
    navController: NavController,
    hotelId: String,
    hotelViewModel: HotelViewModel
) {
    val scrollState = rememberScrollState()

    var hotelModel: HotelModelGet? = null
    for (hotel in hotelViewModel.hotelModels) {
        if(hotel!!.id == hotelId){
            hotelModel = hotel
            break;
        }
    }
    hotelModel!!

    // Track when to show sticky header
    val showStickyHeader by remember {
        derivedStateOf {
            scrollState.value > 100
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Space for sticky header
            Spacer(modifier = Modifier.height(56.dp))

            // Booking schedule card with spacing from header
            BookingScheduleCard(
                hotelViewModel = hotelViewModel,
                hotelModel = hotelModel,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Room list content
            RoomListContent(
                navController = navController,
                hotelModel = hotelModel,
                hotelViewModel = hotelViewModel
            )
        }

        // Sticky header with enhanced shadow when scrolled
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(10f),
            color = Color.White,
            shadowElevation = if (showStickyHeader) 8.dp else 0.dp
        ) {
            Column {
                // Status bar coverage
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                )

                // Header with back button and title
                RoomListHeader(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
