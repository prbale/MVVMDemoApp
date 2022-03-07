package app.bale.demoapplication.repository

import app.bale.demoapplication.model.Deal
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("2aa5fc04-3ef2-45ee-b797-8d1a497f124c")
    fun getAllDeals() : Call<List<Deal>>
}