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
import javax.inject.Inject

class DealsViewModel @Inject constructor(private val repository: DealsRepository) : ViewModel() {

    /**
     * Refer [[DealsUiState]] Sealed class.
     */
    val dealsUiState = MutableLiveData<DealsUiState>()

    private val disposable = CompositeDisposable()

    fun getAllDeals() {

        dealsUiState.value = DealsUiState.Loading

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