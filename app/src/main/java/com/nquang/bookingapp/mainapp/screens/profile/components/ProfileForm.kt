package com.nquang.bookingapp.mainapp.screens.profile.components

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
import com.nquang.bookingapp.mainapp.data.model.account.User
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(
    user: User,
    onUserUpdate: (User) -> Unit,
    onImageEdit: () -> Unit
) {
    var nickname by remember { mutableStateOf(user.nickname ?: "") }
    var phone by remember { mutableStateOf(user.phone) }
    var email by remember { mutableStateOf(user.email) }
    var gender by remember { mutableStateOf(user.gender ?: "") }
    var birthDate by remember { mutableStateOf(user.birthDate ?: "") }

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
                if (user.avatarUrl != null) {
                    // Hiển thị ảnh đại diện nếu có
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    val displayText = if (nickname.isNotEmpty()) {
                        nickname.first().toString().uppercase(Locale.getDefault())
                    } else {
                        user.name.first().toString().uppercase(Locale.getDefault())
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
            value = nickname,
            onValueChange = {
                nickname = it
                onUserUpdate(user.copy(nickname = it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone number with flag
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Số điện thoại",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.width(100.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Vietnam flag
                Box(
                    modifier = Modifier
                        .size(24.dp, 16.dp)
                        .background(Color.Red, RoundedCornerShape(2.dp))
                ) {
                    // Simplified flag representation
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = phone,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Email",
            value = email,
            onValueChange = {
                email = it
                onUserUpdate(user.copy(email = it))
            }
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
            value = gender,
            onValueChange = {
                gender = it
                onUserUpdate(user.copy(gender = it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Ngày sinh",
            value = birthDate,
            onValueChange = {
                birthDate = it
                onUserUpdate(user.copy(birthDate = it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Referral code with QR
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mã mời",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.width(100.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.referralCode ?: "",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    Icons.Default.QrCode,
                    contentDescription = "QR Code",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
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
