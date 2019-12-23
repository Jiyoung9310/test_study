package com.example.mytestapplication.memo.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapplication.memo.data.Converters
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDao
import java.util.*

class MemoEmptyDaoTestImp: MemoDao {
    private val converters = Converters()

    private val _memoList = MutableLiveData<List<Memo>>().apply {
        postValue(listOf<Memo>())
    }

    private val _memo = MutableLiveData<Memo>()

    override fun getMemoList(): LiveData<List<Memo>> {
        return _memoList
    }

    override fun getMemo(memoId: String): LiveData<Memo>? {
        _memoList.value?.forEach {
            if(it.memoId == memoId) return _memo.apply { value = it }
        }
        return null
    }
}