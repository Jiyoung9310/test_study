package com.example.mytestapplication.network

import com.example.mytestapplication.data.ResWeather
import com.example.mytestapplication.data.Weather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapplication.data.X

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