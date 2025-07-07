package com.nquang.bookingapp.mainapp.data.model.transportation

import com.nquang.bookingapp.R

object TransportationMockData {

    val busServices = listOf(
        TransportService(
            id = "bus_1",
            name = "Vé Xe Giường Nằm đi Sapa từ Hà Nội và Ngược Lại",
            company = "Sapa",
            route = "Hà Nội - Sapa",
            rating = 4.1f,
            reviewCount = "9K+",
            price = 320000,
            imageRes = R.drawable.xebuyt1,
            location = "Hà Nội",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_2",
            name = "Vé Xe Limousine Hà Nội - Sapa và Ngược Lại",
            company = "Hà Nội",
            route = "Hà Nội - Sapa",
            rating = 4.3f,
            reviewCount = "30K+",
            price = 441000,
            imageRes = R.drawable.xebuyt2,
            location = "Hà Nội",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_3",
            name = "Vé Xe Buýt Đà Nẵng - Hội An và Ngược Lại",
            company = "Đà Nẵng",
            route = "Đà Nẵng - Hội An",
            rating = 4.6f,
            reviewCount = "10K+",
            price = 143330,
            imageRes = R.drawable.xebuyt3,
            location = "Đà Nẵng",
            type = TransportType.BUS
        )
    )

    val trainServices = listOf(
        TransportService(
            id = "train_1",
            name = "Vé Tàu Hỏa Hà Nội - TP.HCM",
            company = "Đường Sắt Việt Nam",
            route = "Hà Nội - TP.HCM",
            rating = 4.2f,
            reviewCount = "15K+",
            price = 850000,
            imageRes = R.drawable.tauhoa,
            location = "Hà Nội",
            type = TransportType.TRAIN
        ),
        TransportService(
            id = "train_2",
            name = "Vé Tàu Hỏa Hà Nội - Đà Nẵng",
            company = "Đường Sắt Việt Nam",
            route = "Hà Nội - Đà Nẵng",
            rating = 4.0f,
            reviewCount = "8K+",
            price = 650000,
            imageRes = R.drawable.tauhoa,
            location = "Hà Nội",
            type = TransportType.TRAIN
        )
    )

    val carRentalServices = listOf(
        TransportService(
            id = "car_1",
            name = "Thuê Xe 4 Chỗ Toyota Vios",
            company = "Mioto",
            route = "Hà Nội",
            rating = 4.5f,
            reviewCount = "12K+",
            price = 800000,
            imageRes = R.drawable.thuexe,
            location = "Hà Nội",
            type = TransportType.CAR_RENTAL
        )
    )

    val airportShuttleServices = listOf(
        TransportService(
            id = "shuttle_1",
            name = "Xe Đưa Đón Sân Bay Nội Bài",
            company = "Airport Shuttle",
            route = "Nội Bài - Hà Nội",
            rating = 4.3f,
            reviewCount = "5K+",
            price = 150000,
            imageRes = R.drawable.xesanbay,
            location = "Hà Nội",
            type = TransportType.AIRPORT_SHUTTLE
        )
    )

    val flightServices = listOf(
        TransportService(
            id = "flight_1",
            name = "Vé Máy Bay Hà Nội - TP.HCM",
            company = "Vietnam Airlines",
            route = "Hà Nội - TP.HCM",
            rating = 4.4f,
            reviewCount = "25K+",
            price = 2500000,
            imageRes = R.drawable.chuyenbay,
            location = "Hà Nội",
            type = TransportType.FLIGHT
        )
    )

    val privateCarServices = listOf(
        TransportService(
            id = "private_1",
            name = "Thuê Xe Riêng Có Tài Xế",
            company = "Private Car",
            route = "Hà Nội",
            rating = 4.6f,
            reviewCount = "3K+",
            price = 1200000,
            imageRes = R.drawable.thuexerieng,
            location = "Hà Nội",
            type = TransportType.PRIVATE_CAR
        )
    )

    val transportCategories = listOf(
        TransportCategory(
            type = TransportType.BUS,
            title = "Xe buýt",
            icon = "ic_bus",
            backgroundImage = R.drawable.xebuyt
        ),
        TransportCategory(
            type = TransportType.TRAIN,
            title = "Tàu hỏa",
            icon = "ic_train",
            backgroundImage = R.drawable.tauhoa
        ),
        TransportCategory(
            type = TransportType.CAR_RENTAL,
            title = "Thuê xe",
            icon = "ic_car",
            backgroundImage = R.drawable.thuexe
        ),
        TransportCategory(
            type = TransportType.AIRPORT_SHUTTLE,
            title = "Xe sân bay",
            icon = "ic_airport",
            backgroundImage = R.drawable.xesanbay
        ),
        TransportCategory(
            type = TransportType.FLIGHT,
            title = "Chuyến bay",
            icon = "ic_flight",
            backgroundImage = R.drawable.chuyenbay
        ),
        TransportCategory(
            type = TransportType.PRIVATE_CAR,
            title = "Thuê xe riêng",
            icon = "ic_private_car",
            backgroundImage = R.drawable.thuexerieng
        )
    )
}
