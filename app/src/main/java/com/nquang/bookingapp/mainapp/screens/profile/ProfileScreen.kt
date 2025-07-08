package com.nquang.bookingapp.mainapp.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.repository.AccountRepository
import com.nquang.bookingapp.mainapp.screens.profile.components.ProfileForm
import com.nquang.bookingapp.mainapp.screens.profile.components.ProfileHeader
import com.nquang.bookingapp.mainapp.screens.profile.components.ImagePickerDialog
import com.nquang.bookingapp.utils.FirebaseUtils
import com.nquang.bookingapp.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    userViewModel: UserViewModel,
) {
    var showImagePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val userModel = userViewModel.userModel.value
    if(userModel == null){
        Toast.makeText(context, "User model is null", Toast.LENGTH_SHORT).show()
        return
    }

    var userModelCopy by remember { mutableStateOf(userModel.copy()) } ;

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
                onImageEdit = {
                    showImagePicker = true
                },
                userViewModel = userViewModel
            )
        }

        // Nút cập nhật
        Button(
            onClick = {
                if(userModelCopy != userViewModel.userModel.value) {
                    val phoneNumber = userModel.phoneNumber
                    if(phoneNumber!= null){
                        if(phoneNumber.length != 10 || !phoneNumber.matches(Regex("0[0-9]+"))){
                            Toast.makeText(context, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                    }
                    FirebaseUtils.saveUserdata(userViewModel.userModel.value!!)
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    userModelCopy = userModel.copy()
                }
            },
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
                userViewModel.userModel.value = userModel.copy(avatarUrl = imageUri)
                showImagePicker = false
            }
        )
    }
}
