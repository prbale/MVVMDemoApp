package app.bale.demoapplication.ui.deals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bale.demoapplication.model.Deal
import app.bale.demoapplication.repository.DealsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DealsViewModel constructor(private val repository: DealsRepository) : ViewModel() {

    val dealsUiState = MutableLiveData<DealsUiState>()

    fun getAllDeals() {

        dealsUiState.value = DealsUiState.Loading

        val response = repository.getAllDeals()
        response.enqueue(object : Callback<List<Deal>> {
            override fun onResponse(call: Call<List<Deal>>, response: Response<List<Deal>>) {
                dealsUiState.postValue(DealsUiState.Success(response.body()))
            }

            override fun onFailure(call: Call<List<Deal>>, t: Throwable) {
                dealsUiState.postValue(
                    DealsUiState.Error(
                        t.message ?: "An error has occurred !"
                    )
                )
            }
        })
    }
}