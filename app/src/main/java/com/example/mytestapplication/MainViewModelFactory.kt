package com.example.mytestapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.network.WeatherRepo
import retrofit2.Retrofit

class MainViewModelFactory(val repo: WeatherRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) MainViewModel(repo) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}