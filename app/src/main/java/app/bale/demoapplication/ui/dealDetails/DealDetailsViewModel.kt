package app.bale.demoapplication.ui.dealDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.bale.demoapplication.model.Deal

class DealDetailsViewModel : ViewModel() {

    val dealsList = MutableLiveData<List<Deal>>()

    val errorMessage = MutableLiveData<String>()

    fun displayDealDetails() {

    }
}