package com.nquang.bookingapp.mainapp.screens.account.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.account.MenuItem

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    MenuItemRow(
                        item = item,
                        onClick = { onItemClick(item) }
                    )

                    if (index < items.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.Gray.copy(alpha = 0.2f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuItemRow(
    item: MenuItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Icon(
            imageVector = getIconForMenuItem(item.icon.toString()),
            contentDescription = item.title,
            tint = if (item.isWarning) Color(0xFFFF6B35) else Color.Gray,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Title
        Text(
            text = item.title,
            fontSize = 14.sp,
            color = if (item.isWarning) Color(0xFFFF6B35) else Color.Black,
            modifier = Modifier.weight(1f)
        )

        // Value (if exists)
        item.value?.let { value ->
            Text(
                text = value,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        // Arrow (if needed)
        if (item.hasArrow) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

private fun getIconForMenuItem(iconName: String): ImageVector {
    return when (iconName) {
        "booking" -> Icons.Default.BookOnline
        "favorite" -> Icons.Default.Favorite
        "notification" -> Icons.Default.Notifications
        "language" -> Icons.Default.Language
        "location" -> Icons.Default.LocationOn
        "help" -> Icons.Default.Help
        "terms" -> Icons.Default.Description
        "version" -> Icons.Default.Info
        "contact" -> Icons.Default.ContactSupport
        "logout" -> Icons.Default.ExitToApp
        else -> Icons.Default.Settings
    }
}
