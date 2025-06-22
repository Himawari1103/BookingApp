package com.example.mainapp.data.repository

import com.example.mainapp.data.model.busdetail.BusDetailMockData
import com.example.mainapp.data.model.busdetail.BusServiceDetail
import com.example.mainapp.data.model.busdetail.BusReview

class BusDetailRepository {
    
    fun getBusServiceDetail(serviceId: String): BusServiceDetail {
        return BusDetailMockData.getBusServiceDetail(serviceId)
    }
    
    fun getBusReviews(serviceId: String): List<BusReview> {
        return BusDetailMockData.getBusReviews(serviceId)
    }
}
