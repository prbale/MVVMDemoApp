package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("2aa5fc04-3ef2-45ee-b797-8d1a497f124c")
    suspend fun getAllDeals() : Response<List<Deal>>
}