package app.bale.demoapplication.repository

import javax.inject.Inject

class DealsRepository @Inject constructor(private val retrofitService: RetrofitService) {

    fun getAllDeals() = retrofitService.getAllDeals()
}