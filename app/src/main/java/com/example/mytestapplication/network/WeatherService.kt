package com.example.mytestapplication.network

import com.example.mytestapplication.data.ResWeather
import retrofit2.Call
import retrofit2.http.*

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    fun getWeather(@Query("id") id: String,
                   @Query("appid") appid: String): Call<ResWeather>

}