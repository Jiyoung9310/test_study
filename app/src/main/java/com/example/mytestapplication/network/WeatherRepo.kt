package com.example.mytestapplication.network

import ResWeather
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response

interface WeatherRepo {
    fun getWeatherInfo(id: String, appid: String) : LiveData<ResWeather>
}