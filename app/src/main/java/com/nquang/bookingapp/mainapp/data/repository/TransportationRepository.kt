package com.example.mainapp.data.repository

import com.example.mainapp.data.model.transportation.TransportService
import com.example.mainapp.data.model.transportation.TransportCategory
import com.example.mainapp.data.model.transportation.TransportType
import com.example.mainapp.data.model.transportation.TransportationMockData

class TransportationRepository {

    fun getTransportCategories(): List<TransportCategory> {
        return TransportationMockData.transportCategories
    }

    fun getServicesByType(type: TransportType): List<TransportService> {
        return when (type) {
            TransportType.BUS -> TransportationMockData.busServices
            TransportType.TRAIN -> TransportationMockData.trainServices
            TransportType.CAR_RENTAL -> TransportationMockData.carRentalServices
            TransportType.AIRPORT_SHUTTLE -> TransportationMockData.airportShuttleServices
            TransportType.FLIGHT -> TransportationMockData.flightServices
            TransportType.PRIVATE_CAR -> TransportationMockData.privateCarServices
        }
    }

    fun getServiceById(id: String): TransportService? {
        val allServices = listOf(
            TransportationMockData.busServices,
            TransportationMockData.trainServices,
            TransportationMockData.carRentalServices,
            TransportationMockData.airportShuttleServices,
            TransportationMockData.flightServices,
            TransportationMockData.privateCarServices
        ).flatten()

        return allServices.find { it.id == id }
    }
}
