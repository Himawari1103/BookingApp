package com.example.mainapp.data.hoteldetail

import com.example.mainapp.R
import com.example.mainapp.data.common.Hotel

object HotelDetailMockData {
    
    fun getHotelDetail(hotelId: String): HotelDetail {
        val hotel = getHotelById(hotelId)
        return HotelDetail(
            id = hotel.id,
            name = hotel.name,
            rating = hotel.rating,
            reviewCount = hotel.reviewCount,
            address = hotel.address,
            distance = hotel.distance,
            price = hotel.price,
            originalPrice = hotel.originalPrice,
            imageRes = hotel.imageRes,
            imageCount = hotel.imageCount,
            thumbnailImages = hotel.thumbnailImages,
            amenities = hotel.amenities,
            description = getHotelDescription(hotelId),
            reviews = getHotelReviews(hotelId),
            policies = getHotelPolicies(hotelId)
        )
    }
    
    private fun getHotelById(id: String): Hotel {
        val hotels = listOf(
            Hotel(
                id = "1",
                name = "Grand Hotel Saigon",
                rating = 4.8f,
                reviewCount = 1234,
                address = "8 Đồng Khởi, Quận 1, TP.HCM",
                distance = "2.1km từ trung tâm",
                price = "2,500,000",
                originalPrice = "3,200,000",
                imageRes = R.drawable.hotel1,
                imageCount = 25,
                thumbnailImages = listOf(R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4),
                amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym", "Nhà hàng")
            ),
            Hotel(
                id = "2",
                name = "Lotte Hotel Hanoi",
                rating = 4.7f,
                reviewCount = 987,
                address = "54 Liễu Giai, Ba Đình, Hà Nội",
                distance = "1.5km từ trung tâm",
                price = "3,800,000",
                originalPrice = "4,500,000",
                imageRes = R.drawable.hotel2,
                imageCount = 32,
                thumbnailImages = listOf(R.drawable.hotel1, R.drawable.hotel3, R.drawable.hotel4),
                amenities = listOf("Wi-Fi miễn phí", "Hồ bơi", "Spa", "Phòng gym", "Bar")
            )
        )
        return hotels.find { it.id == id } ?: hotels.first()
    }
    
    private fun getHotelDescription(hotelId: String): String {
        return when (hotelId) {
            "1" -> "Grand Hotel Saigon là khách sạn 5 sao sang trọng nằm tại trung tâm thành phố Hồ Chí Minh. Với thiết kế cổ điển kết hợp hiện đại, khách sạn mang đến trải nghiệm nghỉ dưỡng đẳng cấp với dịch vụ hoàn hảo."
            "2" -> "Lotte Hotel Hanoi tọa lạc tại vị trí đắc địa trong trung tâm Hà Nội, mang đến cho du khách trải nghiệm nghỉ dưỡng cao cấp với tầm nhìn tuyệt đẹp ra hồ Tây và thành phố."
            else -> "Khách sạn cao cấp với dịch vụ chuyên nghiệp và tiện nghi hiện đại."
        }
    }
    
    private fun getHotelReviews(hotelId: String): List<Review> {
        return listOf(
            Review(
                id = "1",
                userName = "Nguyễn Văn A",
                userAvatar = null,
                rating = 5.0f,
                comment = "Khách sạn tuyệt vời! Dịch vụ chuyên nghiệp, phòng ốc sạch sẽ và thoải mái. Sẽ quay lại lần sau.",
                date = "2 ngày trước",
                helpful = 12
            ),
            Review(
                id = "2",
                userName = "Trần Thị B",
                userAvatar = null,
                rating = 4.5f,
                comment = "Vị trí thuận tiện, gần trung tâm. Nhân viên thân thiện. Chỉ có điều giá hơi cao một chút.",
                date = "1 tuần trước",
                helpful = 8
            ),
            Review(
                id = "3",
                userName = "Lê Minh C",
                userAvatar = null,
                rating = 4.8f,
                comment = "Bữa sáng ngon, hồ bơi đẹp. Phòng view thành phố rất ấn tượng.",
                date = "2 tuần trước",
                helpful = 15
            )
        )
    }
    
    private fun getHotelPolicies(hotelId: String): HotelPolicies {
        return HotelPolicies(
            checkIn = "14:00",
            checkOut = "12:00",
            cancellation = "Miễn phí hủy trước 24h",
            childPolicy = "Trẻ em dưới 12 tuổi được miễn phí",
            petPolicy = "Không cho phép thú cưng"
        )
    }
}
