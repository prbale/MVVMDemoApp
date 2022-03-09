package app.bale.demoapplication.ui.dealList

import dagger.Module
import dagger.Provides

@Module
class DealsListModule {

    @Provides
    internal fun provideDealsAdapter(): MainAdapter = MainAdapter()

}