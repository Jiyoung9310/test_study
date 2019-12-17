package com.example.mytestapplication.memo.dao

import androidx.lifecycle.LiveData
import com.example.mytestapplication.memo.data.Converters
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDao
import java.util.*

class MemoDaoTestImp: MemoDao {
    private val converters = Converters()

    private val memoDummyList = listOf<Memo>(
        Memo(memoId = "1", title = "메모1", category = "기타", description = "메모이다1", writeDt = converters.datestampToCalendar(1576593650220)),
        Memo(memoId = "2", title = "메모2", category = "기타", description = "메모이다2", writeDt = converters.datestampToCalendar(1576593650220)),
        Memo(memoId = "3", title = "메모3", category = "기타", description = "메모이다3", writeDt = converters.datestampToCalendar(1576593650220))
    ).stream()

    override fun getMemoList(): LiveData<List<Memo>> {
        return memoDummyList
    }

    override fun getMemo(memoId: String): LiveData<Memo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}