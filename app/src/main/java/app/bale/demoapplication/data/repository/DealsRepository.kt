package app.bale.demoapplication.data.repository

import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DealsRepository @Inject constructor(var retrofitService: RetrofitService) : BaseApiResponse() {

    suspend fun getAllDeals(): Flow<Resource<List<Deal>>> =
        flow<Resource<List<Deal>>> {
            emit( safeApiCall { retrofitService.getAllDeals() } )
        }.flowOn(Dispatchers.IO)

}