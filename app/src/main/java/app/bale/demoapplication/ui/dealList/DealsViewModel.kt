package app.bale.demoapplication.ui.dealList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.util.Resource
import app.bale.demoapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [DealsFragment]
 */
class DealsViewModel @Inject constructor(private val repository: DealsRepository) : BaseViewModel() {

    private val dealsLiveData = MutableLiveData<Resource<List<Deal>>>()
    val deals: LiveData<Resource<List<Deal>>> = dealsLiveData

    fun getAllDeals() = viewModelScope.launch {
        repository.getAllDeals().collect { data ->
            dealsLiveData.value = data
        }
    }
}