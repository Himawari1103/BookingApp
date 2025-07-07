package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.mainapp.data.model.tourism.Destination
import com.nquang.bookingapp.mainapp.data.model.tourism.TourismActivity
import com.nquang.bookingapp.mainapp.data.model.tourism.TourismMockData

class TourismRepository {
    
    fun getAllDestinations(): List<Destination> {
        return TourismMockData.destinations
    }
    
    fun getPopularDestinations(): List<Destination> {
        return TourismMockData.destinations.filter { it.isPopular }
    }
    
    fun searchDestinations(query: String): List<Destination> {
        return TourismMockData.destinations.filter { 
            it.name.contains(query, ignoreCase = true) ||
            it.province.contains(query, ignoreCase = true)
        }
    }
    
    fun getDestinationById(id: String): Destination? {
        return TourismMockData.destinations.find { it.id == id }
    }
    
    fun getActivitiesByDestination(destinationId: String): List<TourismActivity> {
        val destination = getDestinationById(destinationId)
        return TourismMockData.tourismActivities.filter { 
            it.destination == destination?.name 
        }
    }
    
    fun getAllActivities(): List<TourismActivity> {
        return TourismMockData.tourismActivities
    }
}
