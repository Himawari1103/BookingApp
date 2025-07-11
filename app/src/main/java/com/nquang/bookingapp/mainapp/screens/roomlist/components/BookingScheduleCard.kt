package com.nquang.bookingapp.mainapp.screens.roomlist.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nquang.bookingapp.model.HotelModelGet
import com.nquang.bookingapp.model.RoomBookingType
import com.nquang.bookingapp.utils.Utils
import com.nquang.bookingapp.viewmodel.HotelViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScheduleCard(
    hotelViewModel: HotelViewModel,
    hotelModel: HotelModelGet
) {
    // Determine if it's day or night booking based on time
//    val isNightBooking = true // This would be determined by actual booking time
//
//    val cardColor = if (isNightBooking) {
//        Color(0xFFF0F0FF) // Light purple for night
//    } else {
//        Color(0xFFFFF4E6) // Light orange for day
//    }
//
//    val bookingType = if (isNightBooking) "Qua đêm" else "Theo giờ"
//    val duration = if (isNightBooking) "01 đêm" else "02 giờ"
//    val checkInTime = if (isNightBooking) "22:00, 04/06" else "16:30, 05/06"
//    val checkOutTime = if (isNightBooking) "12:00, 05/06" else "18:30, 05/06"

    var checkInDate by remember { mutableStateOf<Long?>(null) }
//    var checkOutDate by remember { mutableStateOf<Long?>(null) }
//    var rentalType by remember { mutableStateOf<String?>(null) }
    var showCheckInPicker by remember { mutableStateOf(false) }
    var showCheckOutPicker by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()

    val roomBookingModel = hotelViewModel.newRoomBookingModel.value

    // Định dạng ngày
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("vi", "VN"))

    // Danh sách loại hình thuê phòng
    val rentalTypes = listOf(
        RoomBookingType.ONLY_NIGHT.value,
        RoomBookingType.ONLY_DAY.value,
        RoomBookingType.FULL_DAY.value
    )

    val checkInTime: String = if (roomBookingModel?.type != null) {
        if (roomBookingModel.type.name == RoomBookingType.ONLY_NIGHT.name) {
            Utils.localTimeToString(hotelModel.policies.checkIn.plusHours(9))!!
        } else if (roomBookingModel.type.name == RoomBookingType.ONLY_DAY.name) {
            Utils.localTimeToString(hotelModel.policies.checkIn)!!
        } else if (roomBookingModel.type.name == RoomBookingType.FULL_DAY.name) {
            Utils.localTimeToString(hotelModel.policies.checkIn)!!
        } else ""
    } else ""

    val checkOutTime: String = if (roomBookingModel?.type != null) {
        if (roomBookingModel.type.name == RoomBookingType.ONLY_NIGHT.name) {
            Utils.localTimeToString(hotelModel.policies.checkIn.plusHours(20))!!
        } else if (roomBookingModel.type.name == RoomBookingType.ONLY_DAY.name) {
            Utils.localTimeToString(hotelModel.policies.checkIn.plusHours(8))!!
        } else if (roomBookingModel.type.name == RoomBookingType.FULL_DAY.name) {
            Utils.localTimeToString(hotelModel.policies.checkOut)!!
        } else ""
    } else ""
    hotelViewModel.updateTimeCheckInDateTimeNewRoomBookingModel(checkInTime)
    hotelViewModel.updateTimeCheckOutDateTimeNewRoomBookingModel(checkOutTime)

    val checkInDateTime = hotelViewModel.newRoomBookingModel.value!!.checkInDateTime
    val checkOutDateTime = hotelViewModel.newRoomBookingModel.value!!.checkOutDateTime
    val currentType = hotelViewModel.newRoomBookingModel.value!!.type

    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val isAfter2PM = currentHour >= 14
    val minCheckInDate = if (isAfter2PM) {
        // Sau 14h, ngày check-in phải từ ngày hôm sau
        calendar.apply { add(Calendar.DAY_OF_MONTH, 1) }.timeInMillis
    } else {
        // Trước 14h, ngày check-in từ ngày hiện tại
        calendar.timeInMillis
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp), // Add spacing from header
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0F0FF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp // Make it more prominent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top row with booking type and change button
            // Dropdown Menu chọn loại hình thuê phòng
            Box {
                OutlinedTextField(
                    value = roomBookingModel?.type?.value ?: rentalTypes[0],
                    onValueChange = {},
                    label = { Text("Loại hình thuê phòng") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Chọn loại hình")
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rentalTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                hotelViewModel.updateTypeNewRoomBookingModel(type)
                                hotelViewModel.updateTimeCheckInDateTimeNewRoomBookingModel(
                                    checkInTime
                                )
                                hotelViewModel.updateTimeCheckOutDateTimeNewRoomBookingModel(
                                    checkOutTime
                                )
                                if (type == RoomBookingType.ONLY_DAY.value) {
                                    hotelViewModel.updateCheckOutDateTimeNewRoomBookingModel(
                                        checkInDateTime
                                            .withHour(checkOutDateTime.hour)
                                            .withMinute(checkOutDateTime.minute)
                                            .withSecond(checkOutDateTime.second)
                                    )
                                } else {
                                    hotelViewModel.updateCheckOutDateTimeNewRoomBookingModel(
                                        checkInDateTime.plusDays(1)
                                            .withHour(checkOutDateTime.hour)
                                            .withMinute(checkOutDateTime.minute)
                                            .withSecond(checkOutDateTime.second)
                                    )
                                }
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            // Trường chọn ngày check-in
            OutlinedTextField(
                value = (if (roomBookingModel?.checkInDateTime != null) Utils.localDateTimeToString(
                    roomBookingModel.checkInDateTime
                )!! else "") + "-" + checkInTime,
                onValueChange = hotelViewModel::updateCheckInDateTimeNewRoomBookingModelWithString,
                label = { Text("Ngày nhận phòng") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { showCheckInPicker = true }) {
                        Icon(
                            Icons.Default.CalendarToday,
                            contentDescription = "Chọn ngày nhận phòng"
                        )
                    }
                }
            )

            // Trường chọn ngày check-out
            OutlinedTextField(
                value = (if (roomBookingModel?.checkOutDateTime != null) Utils.localDateTimeToString(
                    roomBookingModel.checkOutDateTime
                )!! else "") + "-" + checkOutTime,
                onValueChange = hotelViewModel::updateCheckOutDateTimeNewRoomBookingModelWithString,
                label = { Text("Ngày trả phòng") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { showCheckOutPicker = true }) {
                        Icon(
                            Icons.Default.CalendarToday,
                            contentDescription = "Chọn ngày trả phòng"
                        )
                    }
                }
            )
        }
    }

    // Date Picker for check-in
    if (showCheckInPicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = checkInDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .toInstant().toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { showCheckInPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDate ->
                        val selectedDateTime = Instant.ofEpochMilli(
                            selectedDate
                        ).atZone(
                            ZoneId.of("Asia/Ho_Chi_Minh")
                        ).toLocalDateTime().withHour(checkInDateTime.hour)
                            .withMinute(checkInDateTime.minute)
                            .withSecond(checkInDateTime.second)
                        if (selectedDateTime.isAfter(LocalDateTime.now())) {
                            hotelViewModel.updateCheckInDateTimeNewRoomBookingModel(selectedDateTime)
                            if (currentType == RoomBookingType.ONLY_DAY) {
                                hotelViewModel.updateCheckOutDateTimeNewRoomBookingModel(
                                    selectedDateTime
                                        .withHour(checkOutDateTime.hour)
                                        .withMinute(checkOutDateTime.minute)
                                        .withSecond(checkOutDateTime.second)
                                )
                            } else {
                                hotelViewModel.updateCheckOutDateTimeNewRoomBookingModel(
                                    selectedDateTime.plusDays(1)
                                        .withHour(checkOutDateTime.hour)
                                        .withMinute(checkOutDateTime.minute)
                                        .withSecond(checkOutDateTime.second)
                                )
                            }
                        } else {
                            val message = if (isAfter2PM) {
                                "Vui lòng chọn ngày từ ngày mai trở đi"
                            } else {
                                "Vui lòng chọn ngày từ hôm nay trở đi"
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    showCheckInPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCheckInPicker = false }) {
                    Text("Hủy")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Date Picker for check-out
    if (showCheckOutPicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = checkOutDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .toInstant().toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { showCheckOutPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDate ->
                        val selectedDateTime = Instant.ofEpochMilli(
                            selectedDate
                        ).atZone(
                            ZoneId.of("Asia/Ho_Chi_Minh")
                        ).toLocalDateTime().withHour(checkOutDateTime.hour)
                            .withMinute(checkOutDateTime.minute)
                            .withSecond(checkOutDateTime.second)
                        if (selectedDateTime.isAfter(checkInDateTime)) {
                            hotelViewModel.updateCheckOutDateTimeNewRoomBookingModel(
                                selectedDateTime
                            )
                            if (currentType == RoomBookingType.ONLY_NIGHT) {
                                hotelViewModel.updateCheckInDateTimeNewRoomBookingModel(
                                    selectedDateTime.minusDays(1)
                                        .withHour(checkInDateTime.hour)
                                        .withMinute(checkInDateTime.minute)
                                        .withSecond(checkInDateTime.second)
                                )
                            } else if (currentType == RoomBookingType.ONLY_DAY) {
                                hotelViewModel.updateCheckInDateTimeNewRoomBookingModel(
                                    selectedDateTime
                                        .withHour(checkInDateTime.hour)
                                        .withMinute(checkInDateTime.minute)
                                        .withSecond(checkInDateTime.second)
                                )
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Ngày trả phòng phải sau ngày nhận phòng",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    showCheckOutPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCheckOutPicker = false }) {
                    Text("Hủy")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
