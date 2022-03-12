package app.bale.demoapplication.ui.dealList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DealsViewModel @Inject constructor(private val repository: DealsRepository) : BaseViewModel() {

    /**
     * Refer [[DealsUiState]] Sealed class.
     */
    val dealsUiState = MutableLiveData<DealsUiState>()

    fun getAllDeals() {

        dealsUiState.value = DealsUiState.Loading

        compositeDisposable.add(
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
}