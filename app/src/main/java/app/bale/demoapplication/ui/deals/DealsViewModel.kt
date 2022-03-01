package app.bale.demoapplication.ui.deals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.bale.demoapplication.model.Deal
import app.bale.demoapplication.repository.DealsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealsViewModel constructor(private val repository: DealsRepository): ViewModel() {

    val dealsList = MutableLiveData<List<Deal>>()

    val errorMessage = MutableLiveData<String>()

    fun getAllDeals() {

        val response = repository.getAllDeals()
        response.enqueue(object : Callback<List<Deal>> {
            override fun onResponse(call: Call<List<Deal>>, response: Response<List<Deal>>) {
                dealsList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Deal>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}