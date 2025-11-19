package org.kot.cmpcourse

import android.app.Application
import org.koin.core.context.startKoin
import org.kot.cmpcourse.di.appModule

class CMPApplication : Application() {

    companion object {
        lateinit var cmpApplication: Application
    }

    override fun onCreate() {
        super.onCreate()

        cmpApplication = this

        startKoin {
            modules(appModule)
        }
    }
}