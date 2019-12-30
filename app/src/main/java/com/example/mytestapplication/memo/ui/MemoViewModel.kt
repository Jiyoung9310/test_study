package com.example.mytestapplication.memo.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.mytestapplication.base.Event
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
class MemoViewModel(memoRepo : MemoRepository) : ViewModel() {
    private val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)

    private val _memoListData = Transformations.map(memoRepo.getMemoList()) {
        val list = arrayListOf<MemoTile>()
        it.forEach { memo ->
            list.add(MemoTile(memo.memoId, memo.title, simpleDateFormat.format(memo.writeDt.time)))
        }
        list
    }
    val memoListData : LiveData<ArrayList<MemoTile>> get() = _memoListData

    private val _showEmptyMessage = MediatorLiveData<Boolean>().apply { this.postValue(true) }
    val showEmptyMessage : LiveData<Boolean> get() = _showEmptyMessage

    private val _floatingButtonEvent = MutableLiveData<Event<Boolean>>()
    val floatingButtonEvent : LiveData<Event<Boolean>> get() = _floatingButtonEvent

    init {
        _showEmptyMessage.addSource(_memoListData) { _showEmptyMessage.value = (_memoListData.value ?: arrayListOf<Memo>()).isEmpty()}
    }

    fun onClickFloatingButton() {
        _floatingButtonEvent.value = Event(true)
    }

}
