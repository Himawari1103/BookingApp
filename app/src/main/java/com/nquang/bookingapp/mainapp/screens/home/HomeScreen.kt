package com.nquang.bookingapp.mainapp.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nquang.bookingapp.mainapp.screens.home.components.*
import com.nquang.bookingapp.viewmodel.HotelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    hotelViewModel: HotelViewModel
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val listState = rememberLazyListState()
    var lastScrollOffset by remember { mutableIntStateOf(0) }
    var isScrollingDown by remember { mutableStateOf(true) }

    // Track scroll direction
    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        isScrollingDown = currentOffset > lastScrollOffset
        lastScrollOffset = currentOffset
    }

    // Detect when to show sticky tab section with scroll direction
    val showStickyTab by remember {
        derivedStateOf {
            val tabSectionIndex = 2
            val tabSectionPassed = listState.firstVisibleItemIndex >= tabSectionIndex ||
                    (listState.firstVisibleItemIndex == tabSectionIndex - 1 &&
                            listState.firstVisibleItemScrollOffset > 1070)

            tabSectionPassed && (isScrollingDown || listState.firstVisibleItemIndex > tabSectionIndex)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main scrollable content
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 96.dp) // Space for search bar (40dp top padding + 56dp search bar height)
        ) {
            // Header Section with Orange Background
            item {
                HeaderSection(navController)
            }

            // Main Content with White Background
//            item {
//                MainContentSection(navController)
//            }

            // Tab Section (normal position in content)
            item {
                StickyTabSection(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }

            // Featured Services Section
            if (selectedTab == 0) {
                item {
                    FeaturedServicesSection(
                        navController,
                        hotelViewModel = hotelViewModel
                    )
                }
            }

            // Add bottom padding for navigation
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Sticky Search Bar at the top with proper spacing from top
        SearchBarComponent(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp) // Space for phone status (time, battery, signal)
                .zIndex(11f)
        )

        // Sticky Tab Section (only show when scrolled past normal tab and scrolling down)
        if (showStickyTab) {
            StickyTabSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 96.dp) // Below search bar
                    .zIndex(10f)
            )
        }

        // Bottom Navigation
        BottomNavigationComponent(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
