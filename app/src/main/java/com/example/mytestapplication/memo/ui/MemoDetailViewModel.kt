package com.example.mytestapplication.memo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDetailData
import com.example.mytestapplication.memo.data.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class MemoDetailViewModel(val memoRepo : MemoRepository) : ViewModel() {

    private val _memoData = MutableLiveData<Memo>()

    private val _memoUIData = Transformations.map(_memoData) { memo ->
        memo?.let {
            MemoDetailData(
                title = memo.title,
                category = memo.category,
                contents = if(memo.description.isNotEmpty()) memo.description else "입력된 내용이 없습니다."
            )
        } ?: MemoDetailData(
            title = "",
            category = "",
            contents = "입력된 내용이 없습니다."
        )
    }

    val memoData : LiveData<MemoDetailData> get() = _memoUIData

    fun reqMemoData(memoId: Long) {
        runBlocking {
            try {
                getMemoData(memoId)
            } catch (e: Exception) {
                Log.d("MemoAddViewModel", "failed database update memo : $e")
            }
        }
    }

    private suspend fun getMemoData(memoId: Long) = coroutineScope {
        val one = async(Dispatchers.IO) {
            try {
                memoRepo.getMemo(memoId)
            } finally {
                Log.d("MemoAddViewModel", "finally database update memo")
            }
        }
        _memoData.postValue(one.await())
    }
}
