package com.example.mytestapplication.memo.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapplication.memo.data.Converters
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDao
import java.util.*

class MemoDaoTestImp: MemoDao {
    private val converters = Converters()

    private val _memoList = MutableLiveData<List<Memo>>().apply {
        postValue(listOf<Memo>(
            Memo(memoId = 1, title = "메모1", category = "기타", description = "메모이다1", writeDt = converters.datestampToCalendar(1576593650220)),
            Memo(memoId = 2, title = "메모2", category = "기타", description = "메모이다2", writeDt = converters.datestampToCalendar(1576593650220)),
            Memo(memoId = 3, title = "메모3", category = "기타", description = "메모이다3", writeDt = converters.datestampToCalendar(1576593650220))
        ))
    }

    override fun getMemoList(): LiveData<List<Memo>> {
        return _memoList
    }

    override fun getMemo(memoId: Long): Memo {
        _memoList.value?.forEach {
            if(it.memoId == memoId) return it
        }
        return Memo(memoId = 0, title = "", category = "", description = "", writeDt = converters.datestampToCalendar(1576593650220))
    }

    override fun upserts(vararg entities: Memo) {

    }

    override fun upsert(entity: Memo) : Long = 0
}