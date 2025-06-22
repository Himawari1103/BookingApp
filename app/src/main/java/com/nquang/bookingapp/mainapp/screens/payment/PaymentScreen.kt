package com.example.mainapp.screens.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.model.payment.*
import com.example.mainapp.screens.payment.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController, roomId: String) {
    var showEditGuestDialog by remember { mutableStateOf(false) }
    var showPaymentMethodScreen by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    var guestInfo by remember {
        mutableStateOf(GuestInfo("+84 986296088", "Giang"))
    }

    val scrollState = rememberScrollState()

    // Mock data
    val bookingInfo = BookingInfo(
        hotelName = "One Villa Hotel 5",
        roomName = "Phòng 1 Giường",
        address = "Tầng 6 11, khu đô thị nam cường, Bắc Từ Liêm, Hà Nội, Vietnam",
        checkInDate = "05/06/2025",
        checkInTime = "16:30",
        checkOutDate = "05/06/2025",
        checkOutTime = "18:30",
        duration = "02 giờ",
        isNightBooking = false
    )

    val paymentSummary = PaymentSummary(
        roomPrice = "250.000",
        totalAmount = "250.000"
    )

    if (showPaymentMethodScreen) {
        PaymentMethodScreen(
            onBackClick = { showPaymentMethodScreen = false },
            onPaymentMethodSelected = { method ->
                selectedPaymentMethod = method
                showPaymentMethodScreen = false
            }
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main scrollable content với padding cho sticky header
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = 96.dp) // Space for sticky header (status bar + header height)
            ) {
                // Content với khoảng cách tốt hơn từ header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp) // Thêm top padding để cách header
                ) {
                    // Hotel selection section
                    HotelSelectionSection(bookingInfo = bookingInfo)

                    // Schedule card - reduced spacing
                    BookingScheduleSection(bookingInfo = bookingInfo)

                    // Guest info section
                    GuestInfoSection(
                        guestInfo = guestInfo,
                        onEditClick = { showEditGuestDialog = true }
                    )

                    // Payment details section
                    PaymentDetailsSection(
                        paymentSummary = paymentSummary,
                        selectedPaymentMethod = selectedPaymentMethod,
                        onSelectPaymentMethod = { showPaymentMethodScreen = true }
                    )

                    // Cancellation policy section
                    CancellationPolicySection()

                    // Bottom spacing for sticky button
                    Spacer(modifier = Modifier.height(120.dp)) // Tăng thêm để tránh bị che
                }
            }

            // Sticky header luôn ở trên cùng
            PaymentHeader(
                onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(10f)
            )

            // Sticky payment button
            PaymentBottomBar(
                totalAmount = paymentSummary.totalAmount,
                selectedPaymentMethod = selectedPaymentMethod,
                onPaymentClick = {
                    // Handle payment
                    println("Processing payment...")
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

        // Edit guest dialog
        if (showEditGuestDialog) {
            EditGuestInfoDialog(
                guestInfo = guestInfo,
                onDismiss = { showEditGuestDialog = false },
                onSave = { newGuestInfo ->
                    guestInfo = newGuestInfo
                    showEditGuestDialog = false
                }
            )
        }
    }
}
