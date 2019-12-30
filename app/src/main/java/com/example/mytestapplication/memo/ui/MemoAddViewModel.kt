package com.example.mytestapplication.memo.ui

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    val memoCounter = MediatorLiveData<Int>()

    private val _doneEvent = SingleLiveEvent<Unit>()
    val  doneEvent get() = _doneEvent
    private val _saveMemoEvent = SingleLiveEvent<Memo>()
    val saveMemoEvent get() = _saveMemoEvent
    private val _navigateDetailEvent = SingleLiveEvent<Unit>()
    val navigateDetailEvent get() = _navigateDetailEvent

    init {
        doneButtonEnable.addSource(memoTitle) { doneButtonEnable.value = validationDoneButton() }
        doneButtonEnable.addSource(memoCategory) { doneButtonEnable.value = validationDoneButton() }
        memoCounter.addSource(memoContents) { memoCounter.value = (memoContents.value?:"").length }
    }

    private fun validationDoneButton() = (memoTitle.value?: "").isNotEmpty() && (memoCategory.value?:"") != categoryArray[0]

    fun changeCategory(index: Int) {
        memoCategory.postValue(categoryArray[index])
    }

    fun onClickDone() {
        _doneEvent.call()
    }

    fun saveMemo() {
        runBlocking {
            try {
                addMemoDatabase()
            } catch (e: Exception) {
                Log.d("MemoAddViewModel", "failed database update memo : $e")
            }
        }
    }

    private suspend fun addMemoDatabase(): Memo = coroutineScope {
        val one = async(Dispatchers.IO) {
            try {
                val memo = Memo(
                    title = memoTitle.value ?: "",
                    category = memoCategory.value ?: "",
                    description = memoContents.value ?: ""
                )
                memoRepo.addMemo(memo)
                memo
            } finally {
                Log.d("MemoAddViewModel", "finally database update memo")
            }
        }
        _saveMemoEvent.postValue(one.await())
        one.await()
    }

    fun navigateDetailEvent() {
        _navigateDetailEvent.call()
    }
}
