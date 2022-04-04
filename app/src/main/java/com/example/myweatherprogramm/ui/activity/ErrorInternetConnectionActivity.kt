package com.example.myweatherprogramm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.droidnet.DroidListener
import com.droidnet.DroidNet
import com.example.myweatherprogramm.R
import com.example.myweatherprogramm.databinding.ActivityErrorInternetConnectionBinding

class ErrorInternetConnectionActivity : AppCompatActivity(), DroidListener {

    private lateinit var binding: ActivityErrorInternetConnectionBinding
    private lateinit var mDroidNet: DroidNet

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityErrorInternetConnectionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mDroidNet = DroidNet.getInstance()
        mDroidNet.addInternetConnectivityListener(this)
        binding.errorImageView.setImageResource(R.drawable.no_data_found_icon)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDroidNet.removeInternetConnectivityChangeListener(this)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected) {
            netIsOn()
        } else {
            netIsOff()
        }
    }

    private fun netIsOn() {
        binding.retryButton.setOnClickListener {
            startActivity(
                Intent(
                    this@ErrorInternetConnectionActivity,
                    MainActivity::class.java
                )
            )
            finish()
        }
    }

    private fun netIsOff() {
        binding.retryButton.setOnClickListener {
            Toast.makeText(
                this@ErrorInternetConnectionActivity,
                "Please, check internet connection!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}