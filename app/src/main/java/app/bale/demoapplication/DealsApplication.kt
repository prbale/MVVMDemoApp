package app.bale.demoapplication

import android.app.Application
import android.content.Context


class DealsApplication: Application() {

    companion object {
        fun get(context: Context): DealsApplication {
            return context.applicationContext as DealsApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

    }

}