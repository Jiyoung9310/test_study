package com.example.mytestapplication.memo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile

class MemoViewModel(memoRepo : MemoRepository) : ViewModel() {
    private val _memoListData = Transformations.map(memoRepo.getMemoList()) {
        val list = arrayListOf<MemoTile>()
        it.forEach { memo ->
            MemoTile(memo.title, memo.writeDt.toString())
        }
        list
    }
    val memoListData : LiveData<ArrayList<MemoTile>> get() = _memoListData

    private val _showEmptyMessage = MutableLiveData<Boolean>()
    val showEmptyMessage : LiveData<Boolean> get() = _showEmptyMessage

    fun isListEmpty(isEmpty : Boolean) {
        _showEmptyMessage.value = isEmpty
    }
}
