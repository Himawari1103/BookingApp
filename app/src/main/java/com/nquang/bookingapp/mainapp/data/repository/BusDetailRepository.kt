package com.nquang.bookingapp.mainapp.data.repository

import com.nquang.bookingapp.mainapp.data.model.busdetail.BusDetailMockData
import com.nquang.bookingapp.mainapp.data.model.busdetail.BusServiceDetail
import com.nquang.bookingapp.mainapp.data.model.busdetail.BusReview

class BusDetailRepository {
    
    fun getBusServiceDetail(serviceId: String): BusServiceDetail {
        return BusDetailMockData.getBusServiceDetail(serviceId)
    }
    
    fun getBusReviews(serviceId: String): List<BusReview> {
        return BusDetailMockData.getBusReviews(serviceId)
    }
}
