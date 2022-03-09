package app.bale.demoapplication.dependencyinjection.builder

import app.bale.demoapplication.ui.MainActivity
import app.bale.demoapplication.ui.dealList.DealsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * The module which provides the android injection service to Activities.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [DealsListModule::class])
    abstract fun bindMainActivity(): MainActivity

}