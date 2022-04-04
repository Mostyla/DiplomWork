package com.example.myweatherprogramm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherprogramm.api.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val data: MutableLiveData<WeatherDTO> = MutableLiveData()

    private fun setResponse(value: WeatherDTO) {
        data.postValue(value)
    }

    fun getResponse(): LiveData<WeatherDTO> {
        return data
    }

    fun load(longitude: Float, latitude: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            setResponse(WeatherRepository().getWeather(longitude, latitude))
        }
    }
}