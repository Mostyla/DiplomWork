package com.example.myweatherprogramm.api

import com.example.myweatherprogramm.model.WeatherDTO
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class WeatherRepository {

    private val okHttpClient = OkHttpClient.Builder().build()
    private val gson = Gson()

    fun getWeather(longitude: Float, latitude: Float): WeatherDTO {
        val urlAdressGetWeather =
            "https://api.openweathermap.org/data/2.5/onecall?lat=$latitude&lon=$longitude&exclude=minutely,hourly&units=metric&appid=${ApiConst.APIKEY}"
        val responseWeather =
            okHttpClient
                .newCall(
                    Request.Builder()
                        .url(urlAdressGetWeather)
                        .build()
                ).execute()
        val jsonString = responseWeather.body()?.string().orEmpty()

        return gson.fromJson(jsonString, WeatherDTO::class.java)
    }
}