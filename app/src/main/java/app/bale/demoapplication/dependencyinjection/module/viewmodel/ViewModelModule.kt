package app.bale.demoapplication.dependencyinjection.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.ui.dealDetails.DealDetailsViewModel
import app.bale.demoapplication.ui.dealList.DealsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Add ViewModels here.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DealsViewModel::class)
    internal abstract fun bindDealsViewModel(viewModel: DealsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DealDetailsViewModel::class)
    internal abstract fun bindDealDetailsViewModel(viewModel: DealDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}