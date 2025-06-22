package com.example.mainapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StickyTabSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 12.dp
    ) {
        Column {
            // Orange top border
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(Color(0xFFFF6B35))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                StickyTabButton(
                    text = "Đề xuất",
                    isSelected = selectedTab == 0,
                    onClick = { onTabSelected(0) }
                )

                Spacer(modifier = Modifier.width(40.dp))

                StickyTabButton(
                    text = "Gần đây",
                    isSelected = selectedTab == 1,
                    onClick = { onTabSelected(1) }
                )
            }
        }
    }
}

@Composable
fun StickyTabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color(0xFFFF6B35) else Color(0xFF666666),
            modifier = Modifier
                .clickable { onClick() }
                .padding(vertical = 4.dp)
        )

        if (isSelected) {
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp)
                    .background(
                        Color(0xFFFF6B35),
                        RoundedCornerShape(1.dp)
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
