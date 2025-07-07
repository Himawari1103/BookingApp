package com.nquang.bookingapp.mainapp.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ImagePickerDialog(
    onDismiss: () -> Unit,
    onImageSelected: (String?) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Chọn ảnh đại diện",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                ImagePickerOption(
                    icon = Icons.Default.CameraAlt,
                    title = "Chụp ảnh",
                    subtitle = "Sử dụng camera để chụp ảnh mới",
                    onClick = {
                        // Giả lập URI ảnh từ camera
                        onImageSelected("camera_image_uri")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ImagePickerOption(
                    icon = Icons.Default.PhotoLibrary,
                    title = "Chọn từ thư viện",
                    subtitle = "Chọn ảnh có sẵn từ thư viện",
                    onClick = {
                        // Giả lập URI ảnh từ thư viện
                        onImageSelected("gallery_image_uri")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ImagePickerOption(
                    icon = Icons.Default.Person,
                    title = "Xóa ảnh đại diện",
                    subtitle = "Sử dụng ảnh đại diện mặc định",
                    onClick = {
                        onImageSelected(null)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = "Hủy",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImagePickerOption(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    Color(0xFFFF6B35).copy(alpha = 0.1f),
                    RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFFFF6B35),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
