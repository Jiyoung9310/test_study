package com.example.mytestapplication.network

import com.example.mytestapplication.data.ResWeather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WeatherRepoImp(private val retrofit: Retrofit) : WeatherRepo {
    override fun getWeatherInfo(id: String, appid: String): LiveData<ResWeather> {
        val result = MutableLiveData<ResWeather>()

        retrofit.create(WeatherService::class.java).getWeather(id, appid).enqueue(object: Callback<ResWeather> {
            override fun onFailure(call: Call<ResWeather>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResWeather>, response: Response<ResWeather>) {
                result.postValue(response.body())
            }
        })
        return result
    }
}