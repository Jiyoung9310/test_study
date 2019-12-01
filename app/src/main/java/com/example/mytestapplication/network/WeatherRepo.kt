package com.example.mytestapplication.network

import com.example.mytestapplication.data.ResWeather
import androidx.lifecycle.LiveData

interface WeatherRepo {
    fun getWeatherInfo(id: String, appid: String) : LiveData<ResWeather>
}