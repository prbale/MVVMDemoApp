package app.bale.demoapplication.dependencyinjection.component

import android.app.Application
import app.bale.demoapplication.DealsApplication
import app.bale.demoapplication.dependencyinjection.builder.ActivityModule
import app.bale.demoapplication.dependencyinjection.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class, AndroidInjectionModule::class,
    AppModule::class, ActivityModule::class
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