package app.bale.demoapplication.dependencyinjection.builder

import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.deals.DealsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * This builder provides android injector service to fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [DealsListModule::class])
    abstract fun bindDealsFragment(): DealsFragment

}