package com.example.mainapp.data.model.transportation

data class TransportService(
    val id: String,
    val name: String,
    val company: String,
    val route: String,
    val rating: Float,
    val reviewCount: String,
    val price: Int,
    val imageRes: Int,
    val location: String,
    val type: TransportType
)

enum class TransportType {
    BUS,
    TRAIN,
    CAR_RENTAL,
    AIRPORT_SHUTTLE,
    FLIGHT,
    PRIVATE_CAR
}

data class TransportCategory(
    val type: TransportType,
    val title: String,
    val icon: String,
    val backgroundImage: Int
)
