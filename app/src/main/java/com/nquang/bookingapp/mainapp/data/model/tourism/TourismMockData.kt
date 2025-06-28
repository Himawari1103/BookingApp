package com.example.mainapp.data.model.tourism

object TourismMockData {

    val destinations = listOf(
        Destination(
            id = "hcm",
            name = "TP Hồ Chí Minh",
            province = "Hồ Chí Minh",
            description = "Thành phố năng động với nhiều hoạt động giải trí",
            isPopular = true
        ),
        Destination(
            id = "hanoi",
            name = "Hà Nội",
            province = "Hà Nội",
            description = "Thủ đô với nhiều di tích lịch sử",
            isPopular = true
        ),
        Destination(
            id = "danang",
            name = "Đà Nẵng",
            province = "Đà Nẵng",
            description = "Thành phố biển với nhiều resort cao cấp",
            isPopular = true
        ),
        Destination(
            id = "phuquoc",
            name = "Phú Quốc",
            province = "Kiên Giang",
            description = "Đảo ngọc với bãi biển tuyệt đẹp",
            isPopular = true
        ),
        Destination(
            id = "nhatrang",
            name = "Nha Trang",
            province = "Khánh Hòa",
            description = "Thành phố biển nổi tiếng",
            isPopular = true
        ),
        Destination(
            id = "hoian",
            name = "Hội An",
            province = "Quảng Nam",
            description = "Phố cổ với kiến trúc độc đáo",
            isPopular = true
        ),
        Destination(
            id = "sapa",
            name = "Sapa",
            province = "Lào Cai",
            description = "Vùng núi với ruộng bậc thang tuyệt đẹp",
            isPopular = true
        ),
        Destination(
            id = "dalat",
            name = "Đà Lạt",
            province = "Lâm Đồng",
            description = "Thành phố ngàn hoa với khí hậu mát mẻ",
            isPopular = true
        ),
        Destination(
            id = "halong",
            name = "Hạ Long",
            province = "Quảng Ninh",
            description = "Vịnh với hàng nghìn đảo đá vôi",
            isPopular = true
        ),
        Destination(
            id = "hue",
            name = "Huế",
            province = "Thừa Thiên Huế",
            description = "Cố đô với nhiều di tích hoàng gia"
        ),
        Destination(
            id = "cantho",
            name = "Cần Thơ",
            province = "Cần Thơ",
            description = "Thành phố lớn nhất miền Tây"
        ),
        Destination(
            id = "vungtau",
            name = "Vũng Tàu",
            province = "Bà Rịa - Vũng Tàu",
            description = "Thành phố biển gần TP.HCM"
        )
    )

    val tourismActivities = listOf(
        TourismActivity(
            id = "tour_1",
            name = "Tour Sài Gòn về đêm",
            description = "Khám phá Sài Gòn về đêm với xe máy",
            price = 450000,
            rating = 4.8f,
            reviewCount = "2.1K+",
            imageUrl = "",
            destination = "TP Hồ Chí Minh",
            category = "Tour",
            duration = "4 giờ"
        ),
        TourismActivity(
            id = "tour_2",
            name = "Tham quan Hồ Gươm",
            description = "Dạo quanh hồ Gươm và khu phố cổ",
            price = 200000,
            rating = 4.5f,
            reviewCount = "1.8K+",
            imageUrl = "",
            destination = "Hà Nội",
            category = "Tour",
            duration = "3 giờ"
        ),
        TourismActivity(
            id = "spa_1",
            name = "Spa thư giãn cao cấp",
            description = "Massage và chăm sóc da chuyên nghiệp",
            price = 800000,
            rating = 4.7f,
            reviewCount = "950+",
            imageUrl = "",
            destination = "Đà Nẵng",
            category = "Spa",
            duration = "2 giờ"
        )
    )
}
