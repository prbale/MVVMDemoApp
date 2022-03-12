package app.bale.demoapplication.ui.dealList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.util.Resource
import app.bale.demoapplication.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [DealsFragment]
 */
class DealsViewModel @Inject constructor(private val repository: DealsRepository) : BaseViewModel() {

    val deals = MutableLiveData<Resource<List<Deal>>>()

    fun getAllDeals() {

        viewModelScope.launch {

            deals.value = Resource.loading(null)

            try {
                val data = repository.getAllDeals()
                deals.value = Resource.success(data)
            }
            catch (error: Exception) {
                deals.value = Resource.error(
                    error.message ?: "An error has occurred !",
                    null)
            }
        }
    }
}