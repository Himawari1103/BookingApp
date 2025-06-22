package com.example.mainapp.screens.reviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.repository.ReviewRepository
import com.example.mainapp.screens.reviews.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsScreen(navController: NavController, hotelId: String) {
    var selectedSortOption by remember { mutableStateOf("suggestions") }
    var selectedFilter by remember { mutableStateOf("all") }
    var showSortDialog by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()
    
    // Mock data
    val overallRating = 4.1f
    val totalReviews = 769
    
    val reviews = ReviewRepository.getReviewsByHotelId(hotelId)
    val sortOptions = ReviewRepository.getSortOptions()
    val filterOptions = ReviewRepository.getFilterOptions()
    val albumImages = ReviewRepository.getAlbumImages()
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 96.dp) // Space for sticky header
        ) {
            // Overall rating section
            OverallRatingSection(
                rating = overallRating,
                totalReviews = totalReviews
            )
            
            // Album hình section
            AlbumSection(images = albumImages)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Sort and filter section
            SortAndFilterSection(
                selectedSortOption = selectedSortOption,
                selectedFilter = selectedFilter,
                sortOptions = sortOptions,
                filterOptions = filterOptions,
                onSortClick = { showSortDialog = true },
                onFilterSelected = { selectedFilter = it }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Reviews list
            ReviewsList(reviews = reviews)
            
            Spacer(modifier = Modifier.height(100.dp))
        }
        
        // Sticky header
        ReviewsHeader(
            onBackClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(10f)
        )
        
        // Sort dialog
        if (showSortDialog) {
            SortDialog(
                options = sortOptions,
                selectedOption = selectedSortOption,
                onOptionSelected = { selectedSortOption = it },
                onDismiss = { showSortDialog = false }
            )
        }
    }
}
