package app.bale.demoapplication.di

import app.bale.demoapplication.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [
        FragmentBuilderModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity
}