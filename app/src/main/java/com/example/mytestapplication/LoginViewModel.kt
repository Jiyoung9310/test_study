package com.example.mytestapplication

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val userID = MutableLiveData<String>().apply { postValue("") }
    val userPW = MutableLiveData<String>().apply { postValue("") }

    private val _loginEnable = MediatorLiveData<Boolean>()
    val loginEnable = _loginEnable


    init {
        _loginEnable.addSource(userID) { _loginEnable.value = (userID.value ?: "").isNotEmpty() && (userPW.value ?: "").isNotEmpty() }
        _loginEnable.addSource(userPW) { _loginEnable.value = (userID.value ?: "").isNotEmpty() && (userPW.value ?: "").isNotEmpty() }
    }
}
