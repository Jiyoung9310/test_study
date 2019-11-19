package com.example.mytestapplication.network

import ResWeather
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    fun getWeather(@Query("id") id: String,
                   @Query("appid") appid: String): Call<ResWeather>

}