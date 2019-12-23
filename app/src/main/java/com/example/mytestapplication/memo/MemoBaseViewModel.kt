package com.example.mytestapplication.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.base.Event
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository

class MemoBaseViewModel(val memoRepo : MemoRepository) : ViewModel() {
    private val _navigateAddEvent = MutableLiveData<Event<Boolean>>()
    val navigateAddEvent : LiveData<Event<Boolean>> get() = _navigateAddEvent

    private val _navigateMainEvent = MutableLiveData<Event<Boolean>>()
    val navigateMainEvent : LiveData<Event<Boolean>> get() = _navigateMainEvent


    fun navigateAddEvent() {
        _navigateAddEvent.value = Event(true)
    }

    fun navigateMainEvent() {
        _navigateMainEvent.value = Event(true)
    }

}
