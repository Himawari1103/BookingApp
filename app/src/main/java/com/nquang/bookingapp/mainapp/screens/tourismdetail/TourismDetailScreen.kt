package com.example.mainapp.screens.tourismdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mainapp.data.repository.TourismDetailRepository
import com.example.mainapp.data.model.tourismdetail.TourismActivity
import com.example.mainapp.screens.tourismdetail.components.TourismDetailHeader
import com.example.mainapp.screens.tourismdetail.components.TourismFilterSection
import com.example.mainapp.screens.tourismdetail.components.TourismActivityCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourismDetailScreen(
    navController: NavController,
    destination: String
) {
    val repository = remember { TourismDetailRepository() }
    val scope = rememberCoroutineScope()
    
    var activities by remember { mutableStateOf<List<TourismActivity>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var showFilterDialog by remember { mutableStateOf(false) }
    
    // Load activities when screen opens
    LaunchedEffect(destination) {
        scope.launch {
            try {
                activities = repository.getActivitiesByDestination(destination)
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading = false
            }
        }
    }
    
    // Handle search
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            scope.launch {
                activities = repository.searchActivities(destination, searchQuery)
            }
        } else {
            scope.launch {
                activities = repository.getActivitiesByDestination(destination)
            }
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Sticky Header
        TourismDetailHeader(
            destination = destination,
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onBackClick = { navController.popBackStack() },
            onFavoriteClick = { /* Handle favorite */ },
            onMenuClick = { /* Handle menu */ }
        )
        
        // Filter Section
        TourismFilterSection(
            resultCount = activities.size,
            onFilterClick = { showFilterDialog = true }
        )
        
        // Activities List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(activities) { activity ->
                    TourismActivityCard(
                        activity = activity,
                        onFavoriteClick = { activityId ->
                            scope.launch {
                                repository.toggleFavorite(activityId)
                                // Update UI state
                                activities = activities.map { 
                                    if (it.id == activityId) {
                                        it.copy(isFavorite = !it.isFavorite)
                                    } else it
                                }
                            }
                        },
                        onCardClick = { activityId ->
                            // Navigate to activity detail
                            // navController.navigate("activity_detail/$activityId")
                        }
                    )
                }
                
                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
