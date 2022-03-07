package app.bale.demoapplication.dependencyinjection.builder

import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.deals.DealsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    DealsListModule::class
])
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [DealsListModule::class])
    abstract fun bindDealsFragment(): DealsFragment

}