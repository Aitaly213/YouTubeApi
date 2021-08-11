package com.geektech.youtubeapi

import android.app.Application
import com.geektech.youtubeapi.di.koinModules
import com.geektech.youtubeapi.ui.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }

}