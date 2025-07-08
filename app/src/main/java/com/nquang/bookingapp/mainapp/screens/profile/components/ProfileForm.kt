package com.nquang.bookingapp.mainapp.screens.profile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.viewmodel.UserViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(
    onImageEdit: () -> Unit,
    userViewModel: UserViewModel
) {
    val userModel = userViewModel.userModel.value
    if(userModel == null){
        Log.d("AccountScreen", "User model is null")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Avatar section
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFF6B35)),
                contentAlignment = Alignment.Center
            ) {
                if (userModel.avatarUrl != null) {
                    // Hiển thị ảnh đại diện nếu có
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    val displayText = if (userModel.nickname != null) {
                        userModel.nickname.first().toString().uppercase(Locale.getDefault())
                    } else {
                        userModel.fullName?.first().toString().uppercase(Locale.getDefault())
                    }
                    Text(
                        text = displayText,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Camera icon để chỉnh sửa ảnh
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(Color(0xFFFF6B35))
                    .clickable { onImageEdit() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.CameraAlt,
                    contentDescription = "Edit photo",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Form fields
        ProfileTextField(
            label = "Nickname",
            value = userViewModel.userModel.value?.nickname ?: "",
            onValueChange = userViewModel::updateNickName
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone number with flag
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Số điện thoại",
//                fontSize = 14.sp,
//                color = Color.Gray,
//                modifier = Modifier.width(100.dp)
//            )

//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.weight(1f)
//            ) {
//                // Vietnam flag
//                Box(
//                    modifier = Modifier
//                        .size(24.dp, 16.dp)
//                        .background(Color.Red, RoundedCornerShape(2.dp))
//                ) {
//                    // Simplified flag representation
//                }
//
//                Spacer(modifier = Modifier.width(8.dp))

//                Text(
//                    text = phone,
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
//            }
//        }
        ProfileTextField(
            label = "Số điện thoại",
            value = userModel.phoneNumber ?: "",
            onValueChange = userViewModel::updatePhoneNumber
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Personal info section
        Text(
            text = "Thông tin cá nhân",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ProfileTextField(
            label = "Giới tính",
            value = userModel.gender ?: "",
            onValueChange = userViewModel::updateGender
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Ngày sinh",
            value = userModel.birthDate ?: "",
            onValueChange = userViewModel::updateBirthDate
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Địa chỉ",
            value = userModel.address ?: "",
            onValueChange = userViewModel::updateAddress
        )

        // Referral code with QR
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Mã mời",
//                fontSize = 14.sp,
//                color = Color.Gray,
//                modifier = Modifier.width(100.dp)
//            )
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = user.referralCode ?: "",
//                    fontSize = 16.sp,
//                    color = Color.Black,
//                    modifier = Modifier.weight(1f)
//                )
//
//                Icon(
//                    Icons.Default.QrCode,
//                    contentDescription = "QR Code",
//                    tint = Color.Gray,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.width(100.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF6B35),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            ),
            singleLine = true
        )
    }
}
