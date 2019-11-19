package com.example.mytestapplication.network

import ResWeather
import Weather
import X
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MockWeatherRepo : WeatherRepo {

    override fun getWeatherInfo(id: String, appid: String): LiveData<ResWeather> {
        val X = arrayListOf<X>(
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow")))
        )
        val res = ResWeather(list = X)

        val result = MutableLiveData<ResWeather>()
        result.postValue(res)
        return result
    }
}