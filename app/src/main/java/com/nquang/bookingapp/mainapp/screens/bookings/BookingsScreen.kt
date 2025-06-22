package com.example.mainapp.screens.bookings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.model.booking.BookingItem
import com.example.mainapp.data.model.booking.BookingStatus
import com.example.mainapp.data.repository.BookingRepository
import com.example.mainapp.screens.bookings.components.*
import com.example.mainapp.screens.home.components.BottomNavigationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsScreen(navController: NavController) {
    var selectedStatus by remember { mutableStateOf<BookingStatus?>(null) }
    var showMenuDialog by remember { mutableStateOf(false) }
    var selectedBooking by remember { mutableStateOf<BookingItem?>(null) }

    // Get all bookings and calculate counts
    val allBookings = BookingRepository.getAllBookings()
    val bookingCounts = BookingStatus.values().associateWith { status ->
        allBookings.count { it.status == status }
    }

    // Filter bookings based on selected status
    val filteredBookings = if (selectedStatus == null) {
        allBookings
    } else {
        allBookings.filter { it.status == selectedStatus }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            BookingHeader(
                onBackClick = { navController.popBackStack() },
                modifier = Modifier.zIndex(10f)
            )

            // Status tabs
            BookingStatusTabs(
                selectedStatus = selectedStatus,
                onStatusSelected = { selectedStatus = it },
                bookingCounts = bookingCounts
            )

            // Bookings list
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (filteredBookings.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Không có đặt phòng nào",
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    items(filteredBookings) { booking ->
                        BookingCard(
                            booking = booking,
                            onMenuClick = {
                                selectedBooking = it
                                showMenuDialog = true
                            },
                            onCardClick = { booking ->
                                // Navigate to booking detail
                                navController.navigate("booking_detail/${booking.id}")
                            }
                        )
                    }
                }

                // Bottom spacing for navigation bar
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Bottom Navigation
        BottomNavigationComponent(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // Menu dialog
        if (showMenuDialog && selectedBooking != null) {
            BookingMenuDialog(
                booking = selectedBooking!!,
                onDismiss = {
                    showMenuDialog = false
                    selectedBooking = null
                },
                onDelete = { booking ->
                    // Handle delete
                    BookingRepository.deleteBooking(booking.id)
                    // Show success message or refresh list
                },
                onReportError = { booking ->
                    // Handle report error
                    BookingRepository.reportError(booking.id)
                    // Show success message
                }
            )
        }
    }
}
