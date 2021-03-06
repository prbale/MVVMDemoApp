package app.bale.demoapplication

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import app.bale.demoapplication.dependencyinjection.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class DealsApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    companion object {
        lateinit var applicationInstance: DealsApplication
    }

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    private fun initializeComponent() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

}