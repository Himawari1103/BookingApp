package com.example.mainapp.data.model.transportation

import com.example.mainapp.R

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
            imageRes = R.drawable.xebuyt1, // Sử dụng R.drawable
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
        ),
        TransportService(
            id = "bus_4",
            name = "Vé Xe Giường Nằm TP.HCM - Đà Lạt",
            company = "Saigon Tourist",
            route = "TP.HCM - Đà Lạt",
            rating = 4.4f,
            reviewCount = "15K+",
            price = 350000,
            imageRes = R.drawable.xebuyt4,
            location = "TP Hồ Chí Minh",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_5",
            name = "Vé Xe Buýt Đi Chùng đi giữa Phnom Penh và Cao Cấp",
            company = "Mekong Express",
            route = "Phnom Penh - Cao Cấp",
            rating = 4.4f,
            reviewCount = "10K+",
            price = 530000,
            imageRes = R.drawable.xebuyt5,
            location = "Phnom Penh",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_6",
            name = "Vé Xe Buýt Nha Trang - Đà Lạt",
            company = "Phuong Trang",
            route = "Nha Trang - Đà Lạt",
            rating = 4.2f,
            reviewCount = "8K+",
            price = 180000,
            imageRes = R.drawable.xebuyt6,
            location = "Nha Trang",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_7",
            name = "Vé Xe Limousine Hà Nội - Hạ Long",
            company = "Queen Cafe",
            route = "Hà Nội - Hạ Long",
            rating = 4.5f,
            reviewCount = "12K+",
            price = 250000,
            imageRes = R.drawable.xebuyt7,
            location = "Hà Nội",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_8",
            name = "Vé Xe Buýt TP.HCM - Vũng Tàu",
            company = "Kumho Samco",
            route = "TP.HCM - Vũng Tàu",
            rating = 4.3f,
            reviewCount = "20K+",
            price = 120000,
            imageRes = R.drawable.xebuyt8,
            location = "TP Hồ Chí Minh",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_9",
            name = "Vé Xe Giường Nằm Hà Nội - Ninh Bình",
            company = "Hoang Long",
            route = "Hà Nội - Ninh Bình",
            rating = 4.0f,
            reviewCount = "6K+",
            price = 150000,
            imageRes = R.drawable.xebuyt9,
            location = "Hà Nội",
            type = TransportType.BUS
        ),
        TransportService(
            id = "bus_10",
            name = "Vé Xe Buýt Cần Thơ - TP.HCM",
            company = "Mai Linh Express",
            route = "Cần Thơ - TP.HCM",
            rating = 4.1f,
            reviewCount = "14K+",
            price = 200000,
            imageRes = R.drawable.xebuyt10,
            location = "Cần Thơ",
            type = TransportType.BUS
        )
    )

    val transportCategories = listOf(
        TransportCategory(TransportType.BUS, "Xe buýt", "ic_bus"),
        TransportCategory(TransportType.TRAIN, "Tàu hỏa", "ic_train"),
        TransportCategory(TransportType.CAR_RENTAL, "Thuê xe", "ic_car"),
        TransportCategory(TransportType.AIRPORT_SHUTTLE, "Xe sân bay", "ic_airport"),
        TransportCategory(TransportType.FLIGHT, "Chuyến bay", "ic_flight"),
        TransportCategory(TransportType.PRIVATE_CAR, "Thuê xe riêng", "ic_private_car")
    )
}
