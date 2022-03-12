package app.bale.demoapplication.ui.dealList

import androidx.lifecycle.MutableLiveData
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.util.Resource
import app.bale.demoapplication.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DealsViewModel @Inject constructor(private val repository: DealsRepository) : BaseViewModel() {

    val deals = MutableLiveData<Resource<List<Deal>>>()

    fun getAllDeals() {

        deals.value = Resource.loading(null)

        compositeDisposable.add(
            repository.getAllDeals().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object: DisposableSingleObserver<List<Deal>>() {
                    override fun onSuccess(value: List<Deal>?) {
                        deals.value = Resource.success(value)
                    }

                    override fun onError(e: Throwable?) {
                        deals.value = Resource.error(e?.message ?: "An error has occurred !", null)
                    }

                })
        )

    }
}