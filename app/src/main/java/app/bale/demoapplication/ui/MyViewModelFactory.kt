package app.bale.demoapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.ui.deals.DealsViewModel

class MyViewModelFactory constructor(private val repository: DealsRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DealsViewModel::class.java)) {
            DealsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}