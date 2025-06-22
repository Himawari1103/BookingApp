package com.example.mainapp.data.model.busdetail

import com.example.mainapp.R

object BusDetailMockData {
    
    fun getBusServiceDetail(serviceId: String): BusServiceDetail {
        return BusServiceDetail(
            id = serviceId,
            name = "Vé Xe Giường Nằm đi Sapa từ Hà Nội và Ngược Lại",
            company = "Sapa Express",
            route = "Hà Nội - Sapa",
            rating = 4.1f,
            reviewCount = 769,
            price = 320000,
            imageRes = R.drawable.xebuyt1,
            location = "Hà Nội",
            images = listOf(
                R.drawable.xebuyt1,
                R.drawable.xebuyt2,
                R.drawable.xebuyt3,
                R.drawable.xebuyt4
            ),
            description = "Khám phá vùng núi Tây Bắc thơ mộng trên xe giường nằm đi Sapa từ Hà Nội và ngược lại! Những chiếc xe giường nằm Hà Nội - Sapa được trang bị đầy đủ tiện nghi hiện đại như wifi, điều hòa không khí, giường êm ái với nội thất nhức nâng tầm trải nghiệm đảm bảo thời mai và riêng tư cho hành khách trong suốt chặng đường.",
            amenities = listOf(
                "Xe giường nằm cao cấp 40 chỗ",
                "Wifi miễn phí",
                "Điều hòa 2 chiều",
                "Chăn gối êm ái",
                "Nước uống miễn phí",
                "Đón tận nơi trong nội thành"
            ),
            highlights = listOf(
                BusHighlight(
                    title = "Xe giường nằm cao cấp",
                    description = "Ghế nằm rộng rãi, thoải mái với chăn gối êm ái",
                    imageRes = R.drawable.xebuyt1
                ),
                BusHighlight(
                    title = "Dịch vụ đón tận nơi",
                    description = "Đón khách tại nhà trong nội thành Hà Nội",
                    imageRes = R.drawable.xebuyt2
                ),
                BusHighlight(
                    title = "An toàn & Tin cậy",
                    description = "Tài xế kinh nghiệm, xe được bảo dưỡng định kỳ",
                    imageRes = R.drawable.xebuyt3
                )
            )
        )
    }
    
    fun getBusReviews(serviceId: String): List<BusReview> {
        return listOf(
            BusReview(
                id = "1",
                userName = "Klook User",
                rating = 5,
                comment = "Dễ dàng đổi quá với hướng dẫn từng bước. Cách nhanh chóng và giá cả phải chăng đ...",
                date = "11/3/2024"
            ),
            BusReview(
                id = "2",
                userName = "Minh Anh",
                rating = 4,
                comment = "Xe sạch sẽ, tài xế lái xe an toàn. Giường nằm thoải mái, có thể ngủ ngon.",
                date = "10/3/2024"
            ),
            BusReview(
                id = "3",
                userName = "Thanh Hoa",
                rating = 5,
                comment = "Dịch vụ tốt, đúng giờ. Nhân viên thân thiện, hỗ trợ nhiệt tình.",
                date = "9/3/2024"
            )
        )
    }
}
