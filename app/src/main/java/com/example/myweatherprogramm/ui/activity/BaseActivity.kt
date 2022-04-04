package com.example.myweatherprogramm.ui.activity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.droidnet.DroidListener
import com.droidnet.DroidNet

open class BaseActivity : AppCompatActivity(), DroidListener {

    private lateinit var mDroidNet: DroidNet
    private lateinit var onInternetConnectivityChanged: OnInternetConnectivityChanged

    fun initDroidListener(onInternetConnectivityChanged: OnInternetConnectivityChanged) {
        this.onInternetConnectivityChanged = onInternetConnectivityChanged
        mDroidNet = DroidNet.getInstance()
        mDroidNet.addInternetConnectivityListener(this)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        Log.d("CheckInternetConnection", "BaseActivity $isConnected")

        if (!isConnected) {
            mDroidNet.removeInternetConnectivityChangeListener(this)
            onInternetConnectivityChanged.onInternetConnectivityChanged(isConnected)
        }
    }

    interface OnInternetConnectivityChanged {
        fun onInternetConnectivityChanged(isConnected: Boolean)
    }

    override fun onDestroy() {
        Log.d("CheckInternetConnection", "BaseActivity onDestroy")

        mDroidNet.removeInternetConnectivityChangeListener(this)
        super.onDestroy()
    }

}