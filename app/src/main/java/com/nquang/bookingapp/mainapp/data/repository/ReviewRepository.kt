package com.example.mainapp.data.repository

import com.example.mainapp.R
import com.example.mainapp.data.model.reviews.ReviewData
import com.example.mainapp.data.model.reviews.SortOption

object ReviewRepository {

    fun getReviewsByHotelId(hotelId: String): List<ReviewData> {
        return listOf(
            ReviewData(
                id = "1",
                userName = "Minh Trần",
                date = "28/10/2023",
                rating = 5f,
                comment = "Khách sạn tuyệt vời! Phòng ốc sạch sẽ, thoải mái. Nhân viên phục vụ rất thân thiện và chuyên nghiệp. Vị trí thuận tiện, gần trung tâm thành phố. Bữa sáng ngon miệng với nhiều món ăn đa dạng. Hồ bơi và spa rất tuyệt. Chắc chắn sẽ quay lại lần sau khi có dịp đến thành phố này.",
                images = listOf(R.drawable.hotel5, R.drawable.hotel6, R.drawable.hotel7),
                roomType = "Deluxe Room"
            ),
            ReviewData(
                id = "2",
                userName = "Thu Hà",
                date = "25/10/2023",
                rating = 4f,
                comment = "Khách sạn đẹp, phòng rộng rãi và sạch sẽ. View từ phòng nhìn ra thành phố rất đẹp. Dịch vụ tốt, nhân viên nhiệt tình.",
                images = listOf(R.drawable.hotel8, R.drawable.hotel9),
                roomType = "Superior Room"
            ),
            ReviewData(
                id = "3",
                userName = "Văn Nam",
                date = "22/10/2023",
                rating = 5f,
                comment = "Trải nghiệm tuyệt vời tại khách sạn. Phòng được trang bị đầy đủ tiện nghi hiện đại. Nhân viên lễ tân hỗ trợ rất tốt.",
                images = listOf(R.drawable.hotel10, R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4, R.drawable.hotel5),
                roomType = "Suite"
            ),
            ReviewData(
                id = "4",
                userName = "Lan Anh",
                date = "20/10/2023",
                rating = 3f,
                comment = "Khách sạn ổn, vị trí tốt nhưng phòng hơi nhỏ so với mong đợi.",
                roomType = "Standard Room"
            ),
            ReviewData(
                id = "5",
                userName = "Huy Nguyễn",
                date = "2 ngày trước",
                rating = 5f,
                comment = "Phòng sạch sẽ, thơm tho. Giá cả phù hợp, Nhân viên thân thiện",
                roomType = "MINI"
            ),
            ReviewData(
                id = "6",
                userName = "Klook User",
                date = "11/3/2024",
                rating = 4f,
                comment = "Dễ dàng đổi quà với hướng dẫn từng bước. Cách nhanh chóng và giá cả phải chăng..."
            )
        )
    }

    fun getSortOptions(): List<SortOption> {
        return listOf(
            SortOption("suggestions", "Gợi ý"),
            SortOption("newest", "Mới nhất"),
            SortOption("oldest", "Cũ nhất"),
            SortOption("rating_high", "Đánh giá: Cao đến thấp"),
            SortOption("rating_low", "Đánh giá: Thấp đến cao")
        )
    }

    fun getFilterOptions(): List<Pair<String, String>> {
        return listOf(
            "all" to "Tất cả",
            "with_images" to "Chỉ với Hình ảnh",
            "4_plus" to "4.0+",
            "3_plus" to "3.0+",
            "below_3" to "< 3.0"
        )
    }

    fun getAlbumImages(): List<Int> {
        return listOf(
            R.drawable.hotel1,
            R.drawable.hotel2,
            R.drawable.hotel3,
            R.drawable.hotel4
        )
    }

    // Thêm method để lấy reviews cho HotelDetailScreen
    fun getReviewsForHotelDetail(hotelId: String): List<com.example.mainapp.data.model.reviews.ReviewItem> {
        return listOf(
            com.example.mainapp.data.model.reviews.ReviewItem(
                id = "1",
                userName = "Huy Nguyễn",
                date = "2 ngày trước",
                rating = 5f,
                comment = "Phòng sạch sẽ, thơm tho. Giá cả phù hợp, Nhân viên thân thiện",
                roomType = "MINI"
            ),
            com.example.mainapp.data.model.reviews.ReviewItem(
                id = "2",
                userName = "Klook User",
                date = "11/3/2024",
                rating = 4f,
                comment = "Dễ dàng đổi quà với hướng dẫn từng bước. Cách nhanh chóng và giá cả phải chăng..."
            ),
            com.example.mainapp.data.model.reviews.ReviewItem(
                id = "3",
                userName = "Minh Trần",
                date = "1 tu��n trước",
                rating = 5f,
                comment = "Khách sạn tuyệt vời! Vị trí thuận tiện, gần trung tâm. Sẽ quay lại lần sau."
            ),
            com.example.mainapp.data.model.reviews.ReviewItem(
                id = "4",
                userName = "Thu Hà",
                date = "3 ngày trước",
                rating = 4f,
                comment = "Dịch vụ tốt, phòng ốc sạch sẽ. Bữa sáng ngon, view đẹp từ phòng."
            )
        )
    }
}
