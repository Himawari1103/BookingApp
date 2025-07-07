package com.nquang.bookingapp.mainapp.data.model.tourismdetail

import com.nquang.bookingapp.R

object TourismDetailMockData {
    
    fun getActivitiesByDestination(destination: String): List<TourismActivity> {
        return when (destination.lowercase()) {
            "hà nội" -> getHanoiActivities()
            "tp hồ chí minh" -> getHCMActivities()
            "đà nẵng" -> getDanangActivities()
            "phú quốc" -> getPhuQuocActivities()
            "nha trang" -> getNhaTrangActivities()
            else -> getHanoiActivities() // Default to Hanoi
        }
    }
    
    private fun getHanoiActivities(): List<TourismActivity> {
        return listOf(
            TourismActivity(
                id = "hn_001",
                name = "Vé Xem Múa Rối Nước Thăng Long Tại Hà Nội",
                category = "Sự kiện & Show diễn",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy • Xác nhận tức thời",
                rating = 4.6,
                reviewCount = "2,376",
                bookingCount = "60K+",
                price = 125995,
                originalPrice = 150000,
                imageRes = R.drawable.dulich1,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_002",
                name = "Vé Thủy Cung Lotte World Hà Nội",
                category = "Sở thú & Thủy cung",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.4,
                reviewCount = "321",
                bookingCount = "30K+",
                price = 280000,
                imageRes = R.drawable.dulich2,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_003",
                name = "Vé Vào Công VinKE & Thủy Cung Times City ở Hà Nội",
                category = "Sở thú & Thủy cung",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.4,
                reviewCount = "109",
                bookingCount = "9K+",
                price = 170000,
                imageRes = R.drawable.dulich3,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_004",
                name = "Vé Xe Buýt 2 Tầng Hà Nội Hop-on Hop Off",
                category = "Hop on hop off",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.4,
                reviewCount = "672",
                bookingCount = "20K+",
                price = 142500,
                imageRes = R.drawable.dulich4,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_005",
                name = "Vé Bảo Tàng Van Gogh Alive Hà Nội",
                category = "Bảo tàng",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.5,
                reviewCount = "234",
                bookingCount = "15K+",
                price = 200000,
                imageRes = R.drawable.dulich5,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_006",
                name = "Tour Phố Cổ Hà Nội Bằng Xe Máy",
                category = "Tour",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.7,
                reviewCount = "456",
                bookingCount = "25K+",
                price = 350000,
                imageRes = R.drawable.dulich6,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            ),
            TourismActivity(
                id = "hn_007",
                name = "Vé Tham Quan Lăng Chủ Tịch Hồ Chí Minh",
                category = "Di tích lịch sử",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.3,
                reviewCount = "189",
                bookingCount = "12K+",
                price = 50000,
                imageRes = R.drawable.dulich11,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_008",
                name = "Tour Ẩm Thực Phố Cổ Hà Nội",
                category = "Ẩm thực",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.8,
                reviewCount = "567",
                bookingCount = "35K+",
                price = 450000,
                imageRes = R.drawable.dulich8,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            ),
            TourismActivity(
                id = "hn_009",
                name = "Vé Tham Quan Văn Miếu - Quốc Tử Giám",
                category = "Di tích lịch sử",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.2,
                reviewCount = "298",
                bookingCount = "18K+",
                price = 30000,
                imageRes = R.drawable.dulich9,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_010",
                name = "Tour Hồ Tây Bằng Thuyền Kayak",
                category = "Thể thao nước",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.6,
                reviewCount = "123",
                bookingCount = "8K+",
                price = 250000,
                imageRes = R.drawable.dulich10,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            ),
            TourismActivity(
                id = "hn_011",
                name = "Vé Tham Quan Bảo Tàng Dân Tộc Học Việt Nam",
                category = "Bảo tàng",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.1,
                reviewCount = "87",
                bookingCount = "5K+",
                price = 40000,
                imageRes = R.drawable.dulich11,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_012",
                name = "Tour Tham Quan Làng Gốm Bát Tràng",
                category = "Tour",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.4,
                reviewCount = "156",
                bookingCount = "10K+",
                price = 180000,
                imageRes = R.drawable.dulich12,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            ),
            TourismActivity(
                id = "hn_013",
                name = "Vé Tham Quan Chùa Một Cột",
                category = "Di tích tôn giáo",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.0,
                reviewCount = "67",
                bookingCount = "4K+",
                price = 20000,
                imageRes = R.drawable.dulich13,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_014",
                name = "Tour Tham Quan Hoàng Thành Thăng Long",
                category = "Di tích lịch sử",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.3,
                reviewCount = "234",
                bookingCount = "15K+",
                price = 80000,
                imageRes = R.drawable.dulich14,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_015",
                name = "Vé Tham Quan Cầu Long Biên",
                category = "Danh lam thắng cảnh",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Miễn phí",
                rating = 4.2,
                reviewCount = "145",
                bookingCount = "12K+",
                price = 0,
                imageRes = R.drawable.dulich15,
                tags = listOf("Đặt ngay hôm nay", "Miễn phí")
            ),
            TourismActivity(
                id = "hn_016",
                name = "Tour Tham Quan Nhà Tù Hỏa Lò",
                category = "Di tích lịch sử",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.1,
                reviewCount = "98",
                bookingCount = "7K+",
                price = 60000,
                imageRes = R.drawable.dulich16,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_017",
                name = "Vé Tham Quan Bảo Tàng Lịch Sử Quân Sự",
                category = "Bảo tàng",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.0,
                reviewCount = "76",
                bookingCount = "5K+",
                price = 40000,
                imageRes = R.drawable.dulich17,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_018",
                name = "Tour Tham Quan Chợ Đồng Xuân",
                category = "Mua sắm",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Miễn phí hủy",
                rating = 3.9,
                reviewCount = "123",
                bookingCount = "8K+",
                price = 100000,
                imageRes = R.drawable.dulich18,
                tags = listOf("Đặt ngay hôm nay", "Miễn phí hủy")
            ),
            TourismActivity(
                id = "hn_019",
                name = "Vé Tham Quan Đền Ngọc Sơn",
                category = "Di tích tôn giáo",
                location = "Hà Nội",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.2,
                reviewCount = "167",
                bookingCount = "11K+",
                price = 30000,
                imageRes = R.drawable.dulich19,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hn_020",
                name = "Tour Tham Quan Hồ Hoàn Kiếm Ban Đêm",
                category = "Tour",
                location = "Hà Nội",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.5,
                reviewCount = "289",
                bookingCount = "18K+",
                price = 150000,
                imageRes = R.drawable.dulich20,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            )
        )
    }
    
    private fun getHCMActivities(): List<TourismActivity> {
        return listOf(
            TourismActivity(
                id = "hcm_001",
                name = "Vé Tham Quan Dinh Độc Lập",
                category = "Di tích lịch sử",
                location = "TP Hồ Chí Minh",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.5,
                reviewCount = "456",
                bookingCount = "25K+",
                price = 65000,
                imageRes = R.drawable.dulich1,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            ),
            TourismActivity(
                id = "hcm_002",
                name = "Tour Chợ Nổi Cái Răng Cần Thơ",
                category = "Tour",
                location = "TP Hồ Chí Minh",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.7,
                reviewCount = "678",
                bookingCount = "40K+",
                price = 850000,
                imageRes = R.drawable.dulich2,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            )
            // Add more HCM activities...
        )
    }
    
    private fun getDanangActivities(): List<TourismActivity> {
        return listOf(
            TourismActivity(
                id = "dn_001",
                name = "Vé Cáp Treo Bà Nà Hills",
                category = "Công viên giải trí",
                location = "Đà Nẵng",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.6,
                reviewCount = "1,234",
                bookingCount = "80K+",
                price = 750000,
                imageRes = R.drawable.dulich3,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            )
            // Add more Danang activities...
        )
    }
    
    private fun getPhuQuocActivities(): List<TourismActivity> {
        return listOf(
            TourismActivity(
                id = "pq_001",
                name = "Vé Cáp Treo Hòn Thơm",
                category = "Danh lam thắng cảnh",
                location = "Phú Quốc",
                description = "Đặt ngay hôm nay • Xác nhận tức thời",
                rating = 4.8,
                reviewCount = "567",
                bookingCount = "35K+",
                price = 320000,
                imageRes = R.drawable.dulich4,
                tags = listOf("Đặt ngay hôm nay", "Xác nhận tức thời")
            )
            // Add more Phu Quoc activities...
        )
    }
    
    private fun getNhaTrangActivities(): List<TourismActivity> {
        return listOf(
            TourismActivity(
                id = "nt_001",
                name = "Tour 4 Đảo Nha Trang",
                category = "Tour",
                location = "Nha Trang",
                description = "Đặt trước cho ngày mai • Miễn phí hủy",
                rating = 4.4,
                reviewCount = "789",
                bookingCount = "50K+",
                price = 450000,
                imageRes = R.drawable.dulich5,
                tags = listOf("Đặt trước cho ngày mai", "Miễn phí hủy")
            )
            // Add more Nha Trang activities...
        )
    }
}
