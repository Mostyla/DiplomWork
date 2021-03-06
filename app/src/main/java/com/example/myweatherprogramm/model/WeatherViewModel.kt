package com.example.myweatherprogramm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherprogramm.api.RetrofitWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class WeatherViewModel : ViewModel(), KoinComponent {
    val scope = CoroutineScope(Dispatchers.IO)

    private val rep: RetrofitWeatherRepository by inject()
    private val data: MutableLiveData<WeatherDTO> = MutableLiveData()

    private fun setResponse(value: WeatherDTO) {
        data.postValue(value)
    }

    fun getResponse(): LiveData<WeatherDTO> {
        return data
    }

    fun load(longitude: Float, latitude: Float) {
        scope.launch {
            rep.getWeather(longitude, latitude, object :
                RetrofitWeatherRepository.CallbackData {
                override fun onResponse(weatherDTO: WeatherDTO) {
                    setResponse(weatherDTO)
                }

                override fun onFailure() {

                }

            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}