package app.bale.demoapplication.ui.deals

import dagger.Module
import dagger.Provides

@Module
class DealsListModule {

    @Provides
    internal fun provideDealsAdapter(): MainAdapter = MainAdapter()

}