package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.mainapp.data.model.tourismdetail.TourismActivity
import com.nquang.bookingapp.mainapp.data.model.tourismdetail.TourismDetailMockData
import com.nquang.bookingapp.mainapp.data.model.tourismdetail.TourismFilter
import kotlinx.coroutines.delay

class TourismDetailRepository {
    
    suspend fun getActivitiesByDestination(destination: String): List<TourismActivity> {
        delay(500) // Simulate network delay
        return TourismDetailMockData.getActivitiesByDestination(destination)
    }
    
    suspend fun searchActivities(destination: String, query: String): List<TourismActivity> {
        delay(300)
        val allActivities = getActivitiesByDestination(destination)
        return if (query.isBlank()) {
            allActivities
        } else {
            allActivities.filter { activity ->
                activity.name.contains(query, ignoreCase = true) ||
                activity.category.contains(query, ignoreCase = true) ||
                activity.description.contains(query, ignoreCase = true)
            }
        }
    }
    
    suspend fun filterActivities(
        destination: String,
        filter: TourismFilter
    ): List<TourismActivity> {
        delay(300)
        var activities = getActivitiesByDestination(destination)
        
        filter.priceRange?.let { range ->
            activities = activities.filter { it.price in range }
        }
        
        if (filter.categories.isNotEmpty()) {
            activities = activities.filter { activity ->
                filter.categories.any { category ->
                    activity.category.contains(category, ignoreCase = true)
                }
            }
        }
        
        filter.minRating?.let { minRating ->
            activities = activities.filter { it.rating >= minRating }
        }
        
        if (filter.availableToday) {
            activities = activities.filter { it.isAvailableToday }
        }
        
        return activities
    }
    
    suspend fun toggleFavorite(activityId: String): Boolean {
        delay(100)
        // In real app, this would update the database
        return true
    }
}
