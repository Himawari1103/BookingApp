package com.nquang.bookingapp.mainapp.screens.bookings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.screens.bookings.components.BookingCard
import com.nquang.bookingapp.mainapp.screens.bookings.components.BookingHeader
import com.nquang.bookingapp.mainapp.screens.bookings.components.BookingMenuDialog
import com.nquang.bookingapp.mainapp.screens.bookings.components.BookingStatusTabs
import com.nquang.bookingapp.mainapp.screens.home.components.BottomNavigationComponent
import com.nquang.bookingapp.model.RoomBookingModelGet
import com.nquang.bookingapp.model.RoomBookingModelSet
import com.nquang.bookingapp.model.RoomBookingStatus
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsScreen(
    navController: NavController,
    hotelViewModel: HotelViewModel
) {
    var selectedStatus by remember { mutableStateOf<RoomBookingStatus?>(null) }
    var showMenuDialog by remember { mutableStateOf(false) }
    var selectedBooking by remember { mutableStateOf<RoomBookingModelGet?>(null) }

    // Get all bookings and calculate counts
    val allBookings = hotelViewModel.roomBookingModels
    val bookingCounts = RoomBookingStatus.entries.associateWith { status ->
        allBookings.count { it?.status == status }
    }

    // Filter bookings based on selected status
    val filteredBookings = if (selectedStatus == null) {
        allBookings
    } else {
        allBookings.filter { it?.status == selectedStatus }
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
                            booking = booking!!,
                            onMenuClick = {
                                selectedBooking = it
                                showMenuDialog = true
                            },
                            onCardClick = { booking ->
                                // Navigate to booking detail
                                navController.navigate("booking_detail/${booking.id}")
                            },
                            hotelViewModel = hotelViewModel,

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
//                    BookingRepository.deleteBooking(booking.id)
                    hotelViewModel.roomBookingModels.removeIf { it?.id == booking.id }
                    FirebaseUtils.removeRoomBookingById(booking.id)
                    // Show success message or refresh list
                },
                onCancel = { booking ->
                    // Handle report error
                    val roomBookingModel = hotelViewModel.roomBookingModels.filter { it?.id == booking.id }[0]?.copy(status = RoomBookingStatus.CANCELLED)
                    hotelViewModel.roomBookingModels.removeIf { it?.id == booking.id }
                    hotelViewModel.roomBookingModels.add(roomBookingModel)
                    if (roomBookingModel != null) {
                        FirebaseUtils.saveRoomBooking(
                            RoomBookingModelSet(
                                id = roomBookingModel.id,
                                checkInDateTime = Utils.localDateTimeToStringWithTime(roomBookingModel.checkInDateTime)!!,
                                checkOutDateTime = Utils.localDateTimeToStringWithTime(roomBookingModel.checkOutDateTime)!!,
                                status = roomBookingModel.status,
                                type = roomBookingModel.type,
                                roomId = roomBookingModel.roomId,
                                userId = roomBookingModel.userId,
                            )
                        )
                    }
                    // Show success message
                }
            )
        }
    }
}
