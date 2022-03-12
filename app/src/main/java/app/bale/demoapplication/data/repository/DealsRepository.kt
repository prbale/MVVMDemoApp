package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import javax.inject.Inject

class DealsRepository @Inject constructor(var retrofitService: RetrofitService) {

    suspend fun getAllDeals(): List<Deal> = retrofitService.getAllDeals()
}