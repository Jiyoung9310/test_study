package com.example.mytestapplication.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository

class MemoBaseViewModel(val memoRepo : MemoRepository) : ViewModel() {


    private val _homeEvent = MutableLiveData<Boolean>().apply { postValue(false) }
    val homeEvent : LiveData<Boolean> get() = _homeEvent

    private val _signinEvnet = MutableLiveData<Boolean>().apply { postValue(false) }
    val signinEvent : LiveData<Boolean> get() = _signinEvnet

    fun loginSuccess() {
        _homeEvent.postValue(true)
    }

    fun signInEvent() {
        _signinEvnet.postValue(true)
    }

}
