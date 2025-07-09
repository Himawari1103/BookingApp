package com.nquang.bookingapp.mainapp.screens.payment

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.google.firebase.auth.FirebaseAuth
import com.nquang.bookingapp.mainapp.screens.payment.components.*
import com.nquang.bookingapp.mainapp.data.model.payment.BookingInfo
import com.nquang.bookingapp.mainapp.data.model.payment.GuestInfo
import com.nquang.bookingapp.mainapp.data.model.payment.PaymentMethod
import com.nquang.bookingapp.mainapp.data.model.payment.PaymentSummary
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingModelGet
import com.nquang.bookingapp.model.RoomBookingModelSet
import com.nquang.bookingapp.model.RoomBookingStatus
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.model.RoomModelGet
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import com.nquang.bookingapp.viewmodel.UserViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    hotelId: String,
    hotelViewModel: HotelViewModel,
    userViewModel: UserViewModel
) {
    var hotelModel: HotelModelGet? = null
    for (hotel in hotelViewModel.hotelModels) {
        if (hotel!!.id == hotelId) {
            hotelModel = hotel
            break;
        }
    }
    hotelModel!!

    val roomId = hotelViewModel.newRoomBookingModel.value!!.roomId
    var roomModel: RoomModelGet? = null
    for (room in hotelModel.roomList) {
        if (room.id == roomId) {
            roomModel = room
            break;
        }
    }
    roomModel!!

    var paymentSummary: Int = 0;
    paymentSummary = when (hotelViewModel.newRoomBookingModel.value!!.type) {
        RoomBookingType.ONLY_DAY -> {
            roomModel.price / 20 * 8 / 1000 * 1000
        }

        RoomBookingType.ONLY_NIGHT -> {
            roomModel.price / 20 * 11 / 1000 * 1000
        }

        RoomBookingType.FULL_DAY -> {
            roomModel.price * (hotelViewModel.newRoomBookingModel.value!!.checkOutDateTime.dayOfMonth - hotelViewModel.newRoomBookingModel.value!!.checkInDateTime.dayOfMonth)
        }
    }

    var showEditGuestDialog by remember { mutableStateOf(false) }
    var showPaymentMethodScreen by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    var guestInfo by remember {
        mutableStateOf(GuestInfo("+84 986296088", "Giang"))
    }

    val scrollState = rememberScrollState()

    // Mock data
//    val bookingInfo = BookingInfo(
//        hotelName = "One Villa Hotel 5",
//        roomName = "Phòng 1 Giường",
//        address = "Tầng 6 11, khu đô thị nam cường, Bắc Từ Liêm, Hà Nội, Vietnam",
//        checkInDate = "05/06/2025",
//        checkInTime = "16:30",
//        checkOutDate = "05/06/2025",
//        checkOutTime = "18:30",
//        duration = "02 giờ",
//        isNightBooking = false
//    )
//
//    val paymentSummary = PaymentSummary(
//        roomPrice = "250.000",
//        totalAmount = "250.000"
//    )

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
                    HotelSelectionSection(
//                        bookingInfo = bookingInfo,
                        hotelModel = hotelModel,
                        hotelViewModel = hotelViewModel
                    )

                    // Schedule card - reduced spacing
                    BookingScheduleSection(
                        hotelViewModel = hotelViewModel,
                    )

                    // Guest info section
                    GuestInfoSection(
//                        guestInfo = guestInfo,
                        userViewModel = userViewModel,
                        onEditClick = { showEditGuestDialog = true }
                    )

                    // Payment details section
                    PaymentDetailsSection(
                        paymentSummary = paymentSummary.toString(),
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
                totalAmount = paymentSummary.toString(),
                selectedPaymentMethod = selectedPaymentMethod,
                onPaymentClick = {
                    // Handle payment
                    println("Processing payment...")
                    val newRoomBookingModel = RoomBookingModelSet(
                        id = hotelViewModel.newRoomBookingModel.value!!.id,
                        userId = hotelViewModel.newRoomBookingModel.value!!.userId,
                        roomId = hotelViewModel.newRoomBookingModel.value!!.roomId,
                        checkInDateTime = Utils.localDateTimeToStringWithTime(hotelViewModel.newRoomBookingModel.value!!.checkInDateTime)!!,
                        checkOutDateTime = Utils.localDateTimeToStringWithTime(hotelViewModel.newRoomBookingModel.value!!.checkOutDateTime)!!,
                        type = hotelViewModel.newRoomBookingModel.value!!.type,
                        status = hotelViewModel.newRoomBookingModel.value!!.status
                    )
                    var canBooking = true
                    for (roomBooking in hotelViewModel.roomBookingModels) {
                        if (roomBooking?.status == RoomBookingStatus.PENDING && hotelViewModel.newRoomBookingModel.value!!.roomId == roomBooking.roomId && hotelViewModel.newRoomBookingModel.value!!.checkInDateTime.dayOfMonth >= roomBooking.checkInDateTime.dayOfMonth && hotelViewModel.newRoomBookingModel.value!!.checkOutDateTime.dayOfMonth <= roomBooking.checkOutDateTime.dayOfMonth) {
                            canBooking = false
                        }
                    }
                    if (canBooking) {
                        FirebaseUtils.saveRoomBooking(newRoomBookingModel)
                        hotelViewModel.roomBookingModels.add(hotelViewModel.newRoomBookingModel.value)
                        hotelViewModel.newRoomBookingModel = mutableStateOf<RoomBookingModelGet?>(
                            RoomBookingModelGet(
                                Utils.genUUID(),
                                FirebaseAuth.getInstance().currentUser!!.uid,
                                "",
                                LocalDateTime.now(),
                                LocalDateTime.now().plusDays(1),
                                RoomBookingStatus.PENDING,
                                RoomBookingType.ONLY_NIGHT
                            )
                        )
                    }
                    navController.navigate("home")
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
