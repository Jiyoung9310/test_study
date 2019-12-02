package com.example.mytestapplication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BaseViewModel::class.java)) BaseViewModel() as T
        else throw IllegalArgumentException("ViewModel2 Not Found")
    }
}