package app.bale.demoapplication.dependencyinjection.builder

import app.bale.demoapplication.MainActivity
import app.bale.demoapplication.MainActivityModule
import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.deals.DealsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [DealsListModule::class])
    abstract fun bindDealsFragment(): DealsFragment

}