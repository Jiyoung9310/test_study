package com.example.mytestapplication

import ResWeather
import X
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.network.WeatherRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel(val repo: WeatherRepo) : ViewModel() {

    private val _helloEvent = MutableLiveData<String>()
    val helloEvent: LiveData<String>
        get() = _helloEvent

    private val _weatherEvent = Transformations.map(repo.getWeatherInfo("524901", "b1b15e88fa797225412429c1c50c122a1")) {
        mutableListOf<X>().apply {
            it.list.forEach { x -> add(x) }
        }.toList()
    }
    val weatherEvent: LiveData<List<X>>
        get() = _weatherEvent

    private val _errorEvent = MutableLiveData<String>()
    val errorEvent: LiveData<String> get() = _errorEvent


    fun onClickHello() {
        _helloEvent.postValue("Hello World!")
    }

}