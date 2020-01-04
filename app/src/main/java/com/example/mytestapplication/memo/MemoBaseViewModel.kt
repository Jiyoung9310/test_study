package com.example.mytestapplication.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.base.Event
import com.example.mytestapplication.base.SingleLiveEvent
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository

class MemoBaseViewModel(val memoRepo : MemoRepository) : ViewModel() {
    private val _navigateAddEvent = SingleLiveEvent<Boolean>()
    val navigateAddEvent : LiveData<Boolean> get() = _navigateAddEvent

    private val _navigateMainEvent = SingleLiveEvent<Boolean>()
    val navigateMainEvent : LiveData<Boolean> get() = _navigateMainEvent

    private val _navigateDetailEvent = SingleLiveEvent<Long>()
    val navigateDetailEvent : LiveData<Long> get() = _navigateDetailEvent

    fun navigateAddEvent() {
        _navigateAddEvent.postValue(true)
    }

    fun navigateMainEvent() {
        _navigateMainEvent.postValue(true)
    }

    fun navigateDetailEvent(memoId: Long) {
        _navigateDetailEvent.postValue(memoId)
    }

}
