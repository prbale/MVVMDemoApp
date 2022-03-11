package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import io.reactivex.Single
import javax.inject.Inject

class DealsRepository @Inject constructor(var retrofitService: RetrofitService) {

    fun getAllDeals(): Single<List<Deal>> = retrofitService.getAllDeals()
}