package com.nquang.bookingapp.mainapp.screens.reviews.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nquang.bookingapp.mainapp.data.model.reviews.SortOption

@Composable
fun SortAndFilterSection(
    selectedSortOption: String,
    selectedFilter: String,
    sortOptions: List<SortOption>,
    filterOptions: List<Pair<String, String>>,
    onSortClick: () -> Unit,
    onFilterSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Sort dropdown
        Row(
            modifier = Modifier
                .clickable { onSortClick() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sắp xếp theo: ",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = sortOptions.find { it.id == selectedSortOption }?.name ?: "Gợi ý",
                fontSize = 16.sp,
                color = Color(0xFFFF6B35),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Sort",
                tint = Color(0xFFFF6B35),
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Filter chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filterOptions) { (id, name) ->
                FilterChip(
                    onClick = { onFilterSelected(id) },
                    label = { Text(name) },
                    selected = selectedFilter == id,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFFF6B35),
                        selectedLabelColor = Color.White
                    )
                )
            }
        }
    }
}
