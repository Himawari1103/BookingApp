package com.nquang.bookingapp.mainapp.screens.bookingdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CancelBookingDialog(
    onDismiss: () -> Unit,
    onConfirmCancel: (String, String) -> Unit
) {
    var selectedReason by remember { mutableStateOf("") }
    var customReason by remember { mutableStateOf("") }

    val cancellationReasons = listOf(
        "Thay đổi thời gian",
        "Thay đổi loại phòng",
        "Thay đổi khách sạn",
        "Không còn nhu cầu",
        "Quên nhập mã ưu đãi",
        "Khách sạn nhỏ hủy booking",
        "Đặt thử để trải nghiệm, không có nhu cầu",
        "Giá cao hơn giá khách sạn",
        "Lý do khác"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        // Full screen dialog
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Status bar spacing
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = "Hủy đặt phòng",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }

            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Vui lòng chọn lý do hủy",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Reasons list - scrollable content
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(cancellationReasons) { reason ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedReason = reason }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = reason,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )

                            RadioButton(
                                selected = selectedReason == reason,
                                onClick = { selectedReason = reason },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFFF6B35),
                                    unselectedColor = Color.Gray
                                )
                            )
                        }
                    }

                    // Custom reason input
                    if (selectedReason == "Lý do khác") {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = customReason,
                                onValueChange = { customReason = it },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(
                                        "Nhập lý do",
                                        color = Color.Gray
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFFFF6B35),
                                    unfocusedBorderColor = Color(0xFFE0E0E0),
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black
                                ),
                                shape = RoundedCornerShape(8.dp),
                                minLines = 3,
                                maxLines = 5
                            )
                        }
                    }

                    // Bottom spacing
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }

            // Bottom button
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column {
                    Divider(
                        color = Color(0xFFE0E0E0),
                        thickness = 1.dp
                    )

                    Button(
                        onClick = {
                            val reason = if (selectedReason == "Lý do khác") customReason else selectedReason
                            onConfirmCancel(selectedReason, reason)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedReason.isNotEmpty() &&
                                (selectedReason != "Lý do khác" || customReason.isNotEmpty()))
                                Color(0xFFFF6B35) else Color(0xFFE0E0E0)
                        ),
                        shape = RoundedCornerShape(24.dp),
                        enabled = selectedReason.isNotEmpty() &&
                                (selectedReason != "Lý do khác" || customReason.isNotEmpty())
                    ) {
                        Text(
                            text = "Hủy đặt phòng",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (selectedReason.isNotEmpty() &&
                                (selectedReason != "Lý do khác" || customReason.isNotEmpty()))
                                Color.White else Color.Gray
                        )
                    }

                    // Navigation bar spacing
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }
    }
}
