package app.bale.demoapplication.ui.deals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealsViewModel constructor(private val repository: DealsRepository) : ViewModel() {

    /**
     * Refer [[DealsUiState]] Sealed class.
     */
    val dealsUiState = MutableLiveData<DealsUiState>()

    private val disposable = CompositeDisposable()

    fun getAllDeals() {

        dealsUiState.value = DealsUiState.Loading

        // Call repository method
//        val response = repository.getAllDeals()
//
//        response.enqueue(object : Callback<List<Deal>> {
//            override fun onResponse(call: Call<List<Deal>>, response: Response<List<Deal>>) {
//                dealsUiState.postValue(DealsUiState.Success(response.body()))
//            }
//
//            override fun onFailure(call: Call<List<Deal>>, t: Throwable) {
//                dealsUiState.postValue(
//                    DealsUiState.Error(
//                        t.message ?: "An error has occurred !"
//                    )
//                )
//            }
//        })

        disposable.add(
            repository.getAllDeals().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object: DisposableSingleObserver<List<Deal>>() {
                    override fun onSuccess(value: List<Deal>?) {
                        dealsUiState.value = DealsUiState.Success(value)
                    }

                    override fun onError(e: Throwable?) {
                        dealsUiState.value = DealsUiState.Error(e?.message ?: "An error has occurred !")
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}