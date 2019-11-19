package com.example.mytestapplication.network

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.google.gson.Gson


object ApiClient {

    private val BASE_URL = "https://samples.openweathermap.org/"
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            }
            return retrofit!!
        }
}