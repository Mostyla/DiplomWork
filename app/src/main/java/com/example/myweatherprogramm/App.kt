package com.example.myweatherprogramm

import android.app.Application
import com.droidnet.DroidNet
import com.example.myweatherprogramm.di.appBaseModule
import com.example.myweatherprogramm.di.dateItemViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        DroidNet.init(this)
        startKoin {
            androidContext(this@App)
            modules(appBaseModule, dateItemViewModel)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners()
    }

}