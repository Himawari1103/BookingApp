package com.example.mainapp.screens.busdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.mainapp.data.repository.BusDetailRepository
import com.example.mainapp.screens.busdetail.components.*

@Composable
fun BusDetailScreen(
    navController: NavController,
    serviceId: String
) {
    val repository = remember { BusDetailRepository() }
    val busService = remember { repository.getBusServiceDetail(serviceId) }
    val reviews = remember { repository.getBusReviews(serviceId) }
    val scrollState = rememberScrollState()

    // Theo dõi khi nào cần hiển thị sticky header
    val showStickyHeader by remember {
        derivedStateOf {
            scrollState.value > 400
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Image Gallery
            BusImageGallery(
                images = busService.images,
                serviceName = busService.name
            )

            // Bus Service Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Basic Info Section
                BusInfoSection(busService = busService)

                // Service Description
                BusDescriptionSection(
                    description = busService.description,
                    amenities = busService.amenities
                )

                // Reviews Section
                BusReviewsSection(
                    rating = busService.rating,
                    reviewCount = busService.reviewCount,
                    reviews = reviews,
                    onViewAllReviews = {
                        // Navigate to all reviews
                    }
                )

                // Highlights Section
                BusHighlightsSection(highlights = busService.highlights)

                // Bottom spacing for booking bar
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Top bar (when not scrolled)
        if (!showStickyHeader) {
            BusDetailTopBar(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(top = 8.dp)
                    .zIndex(10f)
            )
        }

        // Sticky top bar (when scrolled)
        if (showStickyHeader) {
            StickyBusTopBar(
                navController = navController,
                serviceName = busService.name,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(10f)
            )
        }

        // Bottom booking bar
        BusBookingBottomBar(
            price = busService.price,
            onBookClick = {
                // Navigate to booking
                navController.navigate("bus_booking/${busService.id}")
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
