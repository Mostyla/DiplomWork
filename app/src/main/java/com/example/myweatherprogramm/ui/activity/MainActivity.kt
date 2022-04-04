package com.example.myweatherprogramm.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherprogramm.ui.dialog.SelectCityDialog
import com.example.myweatherprogramm.ui.adapter.WeatherRcViewAdapter
import com.example.myweatherprogramm.model.WeatherViewModel
import com.example.myweatherprogramm.model.CityModel
import com.example.myweatherprogramm.model.WeatherDTO
import com.example.myweatherprogramm.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var viewModel: WeatherViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            getLastLocation()
        } else {
            initActions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDroidListener()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.getResponse().observe(this) {
            runOnUiThread {
                bindData(it)
            }
        }

        initActions()

        getLastLocation()
    }

    private fun initDroidListener() {
        initDroidListener(object : OnInternetConnectivityChanged {
            override fun onInternetConnectivityChanged(isConnected: Boolean) {
                if (!isConnected) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            ErrorInternetConnectionActivity::class.java
                        )
                    )
                    finish()
                }
            }
        })
    }

    private fun initActions() {
        binding.btnSelectCity.setOnClickListener {
            SelectCityDialog(this, object : SelectCityDialog.OnCitySelectListener {
                override fun onCitySelected(cityModel: CityModel) {
                    binding.tvWindSpeed.visibility = View.VISIBLE
                    binding.tvCurrentDate.visibility = View.VISIBLE
                    binding.tvHumidity.visibility = View.VISIBLE
                    scope.launch {
                        viewModel.load(cityModel.longitude, cityModel.latitude)
                    }
                }
            }).show()
        }
    }

    private fun getLastLocation() {
        if (viewModel.getResponse().value == null)
            if (checkSelfPermission()) {
                binding.tvHumidity.visibility = View.VISIBLE
                binding.tvCurrentDate.visibility = View.VISIBLE
                binding.tvWindSpeed.visibility = View.VISIBLE
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        Log.i("ttt", "loc=${location?.latitude},lon=${location?.longitude}")
                        scope.launch {
                            viewModel.load(
                                location!!.longitude.toFloat(),
                                location.latitude.toFloat()
                            )
                        }
                    }
            } else {
                locationPermissions()
            }
    }

    private fun locationPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun checkSelfPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    @SuppressLint("SimpleDateFormat")
    private fun bindData(weatherDTO: WeatherDTO) {

        binding.tvHumidity.visibility = View.VISIBLE
        binding.tvCurrentDate.visibility = View.VISIBLE
        binding.tvWindSpeed.visibility = View.VISIBLE

        val cityName = weatherDTO.timezone
        val weatherDescription = weatherDTO.current.weather[0].description
        val temperature = weatherDTO.current.feelsLike.toInt()
        val windSpeed = weatherDTO.current.windSpeed.toString()
        val humidity = weatherDTO.current.humidity.toString()
        val iconId = weatherDTO.current.weather[0].icon
        val currentDate = convertLongToTime(weatherDTO.current.dt.toLong() * 1000)

        binding.tvCityName.text = cityName
        binding.tvWeatherDescription.text = weatherDescription
        binding.tvTemp.text = "$temperature°C"
        binding.tvWindSpeed.text = "$windSpeed m/sec"
        binding.tvHumidity.text = "$humidity %"
        binding.tvCurrentDate.text = currentDate.toString()

        Glide.with(this).load("http://openweathermap.org/img/wn/$iconId@2x.png")
            .into(binding.ivWeather)

        binding.rcViewWeather.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rcViewWeather.adapter = WeatherRcViewAdapter(weatherDTO.daily)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = java.text.SimpleDateFormat("dd.MM.yyyy")
        return format.format(date)
    }

}


