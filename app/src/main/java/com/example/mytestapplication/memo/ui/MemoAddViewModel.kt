package com.example.mytestapplication.memo.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.mytestapplication.base.Event
import com.example.mytestapplication.base.SingleLiveEvent
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoRepository
import kotlinx.coroutines.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class MemoAddViewModel(val memoRepo : MemoRepository) : ViewModel() {

    val categoryArray = arrayListOf<String>("선택하세요", "할일", "아이디어", "기타")

    val memoTitle = MutableLiveData<String>().apply { postValue("") }
    val memoCategory = MutableLiveData<String>().apply { postValue(categoryArray[0]) }
    val memoContents = MutableLiveData<String>().apply { postValue("") }
    val doneButtonEnable = MediatorLiveData<Boolean>()
    val memoCounter = Transformations.map(memoContents) { it?.length ?: 0 }

    private val _saveMemoEvent = SingleLiveEvent<Long>()
    val saveMemoEvent : LiveData<Long> get() = _saveMemoEvent

    init {
        doneButtonEnable.addSource(memoTitle) { doneButtonEnable.value = validationDoneButton() }
        doneButtonEnable.addSource(memoCategory) { doneButtonEnable.value = validationDoneButton() }
    }

    private fun validationDoneButton() = (memoTitle.value?: "").isNotEmpty() && (memoCategory.value?:"") != categoryArray[0]

    fun changeCategory(index: Int) {
        memoCategory.postValue(categoryArray[index])
    }

    fun onClickDone() {
//        _doneEvent.call()
        runBlocking {
            try {
                addMemoDatabase()
            } catch (e: Exception) {
                Log.d("MemoAddViewModel", "failed database update memo : $e")
            }
        }
    }

    private suspend fun addMemoDatabase() = coroutineScope {
        val one = async(Dispatchers.IO) {
            try {
                memoRepo.addMemo(Memo(
                    title = memoTitle.value ?: "",
                    category = memoCategory.value ?: "",
                    description = memoContents.value ?: ""
                ))
            } finally {
                Log.d("MemoAddViewModel", "finally database update memo")
            }
        }
        _saveMemoEvent.postValue(one.await())
    }
}