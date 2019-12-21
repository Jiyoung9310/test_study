package com.example.mytestapplication.memo.ui

import androidx.lifecycle.*
import com.example.mytestapplication.base.Event
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MemoViewModel(memoRepo : MemoRepository) : ViewModel() {
    private val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)

    private val _memoListData = Transformations.map(memoRepo.getMemoList()) {
        val list = arrayListOf<MemoTile>()
        it.forEach { memo ->
            list.add(MemoTile(memo.title, simpleDateFormat.format(memo.writeDt.time)))
        }
        list
    }
    val memoListData : LiveData<ArrayList<MemoTile>> get() = _memoListData

    private val _showEmptyMessage = MediatorLiveData<Boolean>().apply { this.postValue(true) }
    val showEmptyMessage : LiveData<Boolean> get() = _showEmptyMessage

    init {
        _showEmptyMessage.addSource(_memoListData) { _showEmptyMessage.value = it.isEmpty()}
    }

}
