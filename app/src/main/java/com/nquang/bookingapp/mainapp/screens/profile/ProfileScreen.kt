package com.example.mainapp.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.data.repository.AccountRepository
import com.example.mainapp.screens.profile.components.ProfileForm
import com.example.mainapp.screens.profile.components.ProfileHeader
import com.example.mainapp.screens.profile.components.ImagePickerDialog

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit
) {
    val user = AccountRepository.getCurrentUser()
    var showImagePicker by remember { mutableStateOf(false) }
    var currentUser by remember { mutableStateOf(user) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileHeader(
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileForm(
                user = currentUser,
                onUserUpdate = { updatedUser ->
                    currentUser = updatedUser
                },
                onImageEdit = {
                    showImagePicker = true
                }
            )
        }

        // Nút cập nhật
        Button(
            onClick = { /* Xử lý cập nhật */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6B35)
            )
        ) {
            Text(
                text = "Cập Nhật",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }

    // Dialog chọn ảnh
    if (showImagePicker) {
        ImagePickerDialog(
            onDismiss = { showImagePicker = false },
            onImageSelected = { imageUri ->
                currentUser = currentUser.copy(avatarUrl = imageUri)
                showImagePicker = false
            }
        )
    }
}
