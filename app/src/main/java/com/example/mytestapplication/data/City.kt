package com.example.mytestapplication.data

data class City(
    val country: String,
    val geoname_id: Int,
    val iso2: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val population: Int,
    val type: String
)