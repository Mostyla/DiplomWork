package com.example.myweatherprogramm.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.droidnet.DroidListener
import com.droidnet.DroidNet
import com.example.myweatherprogramm.R
import com.example.myweatherprogramm.databinding.ActivitySplashWeatherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashWeatherActivity : Activity(), DroidListener {

    private lateinit var binding: ActivitySplashWeatherBinding
    private lateinit var mDroidNet: DroidNet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDroidNet = DroidNet.getInstance()
        mDroidNet.addInternetConnectivityListener(this)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleImageAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.topTextView.startAnimation(topAnim)
        binding.middleTextView.startAnimation(middleAnim)
        binding.bottomTextView.startAnimation(bottomAnim)
        binding.imageView.startAnimation(middleImageAnim)
    }

    override fun onDestroy() {
        mDroidNet.removeInternetConnectivityChangeListener(this)
        super.onDestroy()
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        Log.d("CheckInternetConnection", "SplashWeatherActivity $isConnected")

        if (isConnected) {
            netIsOn()
        } else {
            netIsOff()
        }
    }

    private fun netIsOn() {
        CoroutineScope(Dispatchers.Main).launch {
            mDroidNet.removeInternetConnectivityChangeListener(this@SplashWeatherActivity)
            delay(3000)
            startActivity(Intent(this@SplashWeatherActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun netIsOff() {
        CoroutineScope(Dispatchers.Main).launch {
            mDroidNet.removeInternetConnectivityChangeListener(this@SplashWeatherActivity)
            delay(3000)
            startActivity(
                Intent(
                    this@SplashWeatherActivity,
                    ErrorInternetConnectionActivity::class.java
                )
            )
            finish()
        }
    }

}