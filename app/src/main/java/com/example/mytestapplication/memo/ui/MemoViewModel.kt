package com.example.mytestapplication.memo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile

class MemoViewModel(memoRepo : MemoRepository) : ViewModel() {
    val memoListData : LiveData<ArrayList<MemoTile>> = Transformations.map(memoRepo.getMemoList()) {
        val list = arrayListOf<MemoTile>()
        it.forEach { memo ->
            list.add(MemoTile(memo.title, memo.writeDt.toString()))
        }
        list
    }

    private val _showEmptyMessage = MutableLiveData<Boolean>()
    val showEmptyMessage : LiveData<Boolean> get() = _showEmptyMessage

    fun isListEmpty(isEmpty : Boolean) {

    }
}
