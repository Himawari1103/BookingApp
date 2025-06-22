package com.example.mainapp.data.repository

import com.example.mainapp.data.model.transportation.TransportService
import com.example.mainapp.data.model.transportation.TransportType
import com.example.mainapp.data.model.transportation.TransportationMockData

class TransportationRepository {
    
    fun getTransportCategories() = TransportationMockData.transportCategories
    
    fun getServicesByType(type: TransportType): List<TransportService> {
        return when (type) {
            TransportType.BUS -> TransportationMockData.busServices
            else -> emptyList() // Sẽ implement các loại khác sau
        }
    }
    
    fun getAllBusServices() = TransportationMockData.busServices
}
