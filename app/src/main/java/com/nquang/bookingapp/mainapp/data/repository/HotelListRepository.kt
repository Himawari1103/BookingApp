package com.example.mainapp.data.repository

import com.example.mainapp.data.model.hotellist.HotelItem
import com.example.mainapp.data.model.hotellist.HotelFilter
import com.example.mainapp.data.model.hotellist.HotelSortType
import com.example.mainapp.data.model.hotellist.HotelListMockData

object HotelListRepository {
    // Mutable list để có thể update favorite status
    private val _hotels = HotelListMockData.hotels.toMutableList()

    fun getAllHotels(): List<HotelItem> {
        return _hotels
    }

    fun getFavoriteHotels(): List<HotelItem> {
        return _hotels.filter { it.isFavorite }
    }

    fun searchHotels(query: String): List<HotelItem> {
        if (query.isBlank()) return getAllHotels()
        return _hotels.filter { hotel ->
            hotel.name.contains(query, ignoreCase = true) ||
                    hotel.location.contains(query, ignoreCase = true) ||
                    hotel.amenities.any { it.contains(query, ignoreCase = true) }
        }
    }

    fun filterHotels(filter: HotelFilter): List<HotelItem> {
        var filteredHotels = getAllHotels()

        // Filter by price range
        filter.priceRange?.let { (min, max) ->
            filteredHotels = filteredHotels.filter { hotel ->
                val price = hotel.price.replace(",", "").replace(".", "").toIntOrNull() ?: 0
                price in min..max
            }
        }

        // Filter by rating
        filter.rating?.let { minRating ->
            filteredHotels = filteredHotels.filter { it.rating >= minRating }
        }

        // Filter by amenities
        if (filter.amenities.isNotEmpty()) {
            filteredHotels = filteredHotels.filter { hotel ->
                filter.amenities.any { amenity ->
                    hotel.amenities.any { it.contains(amenity, ignoreCase = true) }
                }
            }
        }

        // Sort results
        return when (filter.sortBy) {
            HotelSortType.PRICE_LOW_TO_HIGH -> filteredHotels.sortedBy {
                it.price.replace(",", "").replace(".", "").toIntOrNull() ?: 0
            }
            HotelSortType.PRICE_HIGH_TO_LOW -> filteredHotels.sortedByDescending {
                it.price.replace(",", "").replace(".", "").toIntOrNull() ?: 0
            }
            HotelSortType.RATING -> filteredHotels.sortedByDescending { it.rating }
            HotelSortType.DISTANCE -> filteredHotels.sortedBy { it.distance }
            HotelSortType.RECOMMENDED -> filteredHotels.sortedByDescending { it.rating }
        }
    }

    fun toggleFavorite(hotelId: String): HotelItem? {
        val hotelIndex = _hotels.indexOfFirst { it.id == hotelId }
        if (hotelIndex != -1) {
            val hotel = _hotels[hotelIndex]
            val updatedHotel = hotel.copy(isFavorite = !hotel.isFavorite)
            _hotels[hotelIndex] = updatedHotel
            return updatedHotel
        }
        return null
    }
}
