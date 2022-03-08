package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

    @GET("2aa5fc04-3ef2-45ee-b797-8d1a497f124c")
    fun getAllDeals() : Single<List<Deal>>
}