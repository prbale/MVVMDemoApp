package app.bale.demoapplication.dependencyinjection.component

import android.app.Application
import app.bale.demoapplication.DealsApplication
import app.bale.demoapplication.dependencyinjection.builder.ActivityBuilderModule
import app.bale.demoapplication.dependencyinjection.builder.FragmentBuilderModule
import app.bale.demoapplication.dependencyinjection.module.AppModule
import app.bale.demoapplication.dependencyinjection.module.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: DealsApplication)
}