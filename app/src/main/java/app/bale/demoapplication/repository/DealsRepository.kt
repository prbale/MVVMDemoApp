package app.bale.demoapplication.repository

class DealsRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllDeals() = retrofitService.getAllDeals()
}