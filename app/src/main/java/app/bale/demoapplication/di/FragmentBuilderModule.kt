package app.bale.demoapplication.di

import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeDealsListFragment(): DealsFragment

    @ContributesAndroidInjector
    abstract fun contributeDealDetailsFragment(): DealDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

}
