package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import retrofit2.http.GET

interface RetrofitService {

    @GET("2aa5fc04-3ef2-45ee-b797-8d1a497f124c")
    suspend fun getAllDeals() : List<Deal>
}