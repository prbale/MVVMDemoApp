package app.bale.demoapplication.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import app.bale.demoapplication.DealsApplication
import app.bale.demoapplication.di.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {

    fun init(dealsApplication: DealsApplication) {

        DaggerAppComponent.builder().application(dealsApplication)
            .build().inject(dealsApplication)

        dealsApplication.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

        })

    }

    private fun handleActivity(activity: Activity) {

        if(activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }

        if(activity is FragmentActivity) {

            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object: FragmentManager.FragmentLifecycleCallbacks() {

                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
        }
    }

}