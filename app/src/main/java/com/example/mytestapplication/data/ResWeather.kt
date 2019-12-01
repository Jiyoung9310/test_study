package com.example.mytestapplication.data

data class ResWeather(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<X>,
    val message: Int? = null
)