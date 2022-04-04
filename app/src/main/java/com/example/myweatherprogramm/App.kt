package com.example.myweatherprogramm

import android.app.Application
import com.droidnet.DroidNet

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DroidNet.init(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners()
    }

}