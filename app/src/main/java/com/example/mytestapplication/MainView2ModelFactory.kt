package com.example.mytestapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainView2ModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainView2Model::class.java)) MainView2Model() as T
        else throw IllegalArgumentException("ViewModel2 Not Found")
    }
}