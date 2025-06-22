package com.example.mainapp.screens.bookingdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.repository.BookingRepository
import com.example.mainapp.screens.bookingdetail.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDetailScreen(navController: NavController, bookingId: String) {
    var showMenuDialog by remember { mutableStateOf(false) }
    var showCancelDialog by remember { mutableStateOf(false) }

    val booking = BookingRepository.getAllBookings().find { it.id == bookingId }

    if (booking == null) {
        // Handle booking not found
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Không tìm thấy thông tin đặt phòng")
        }
        return
    }

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 96.dp) // Space for sticky header
        ) {
            // Add spacing after header before status banner
            Spacer(modifier = Modifier.height(16.dp))

            // Status banner
            BookingStatusBanner(status = booking.status)

            // Add spacing after status banner
            Spacer(modifier = Modifier.height(24.dp))

            // Content sections with better spacing
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp) // Consistent spacing between sections
            ) {
                // Hotel selection section
                HotelSelectionSection(
                    booking = booking,
                    onHotelClick = {
                        // Navigate to hotel detail - extract hotel ID from booking
                        navController.navigate("hotel_detail/1")
                    }
                )

                // Schedule section
                BookingScheduleSection(booking = booking)

                // Guest info section
                GuestInfoSection()

                // Payment details section
                PaymentDetailsSection(booking = booking)

                // Cancellation policy section
                CancellationPolicySection(
                    booking = booking,
                    onCancelBooking = { showCancelDialog = true }
                )
            }
        }

        // Sticky header
        BookingDetailHeader(
            onBackClick = { navController.popBackStack() },
            onMenuClick = { showMenuDialog = true },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(10f)
        )

        // Menu dialog
        if (showMenuDialog) {
            BookingDetailMenuDialog(
                booking = booking,
                onDismiss = { showMenuDialog = false },
                onDeleteHistory = { booking ->
                    // Handle delete history
                    BookingRepository.deleteBooking(booking.id)
                },
                onReportError = { booking ->
                    // Handle report error
                    BookingRepository.reportError(booking.id)
                },
                onCancellationPolicy = { booking ->
                    // Show cancellation policy info
                }
            )
        }

        // Cancel booking dialog
        if (showCancelDialog) {
            CancelBookingDialog(
                onDismiss = { showCancelDialog = false },
                onConfirmCancel = { reason, customReason ->
                    // Handle booking cancellation
                    showCancelDialog = false
                    navController.popBackStack()
                }
            )
        }
    }
}
