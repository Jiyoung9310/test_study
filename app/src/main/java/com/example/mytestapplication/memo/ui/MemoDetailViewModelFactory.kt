package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.memo.data.MemoRepository

class MemoDetailViewModelFactory(val memoId : Long, val memoRepo : MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MemoDetailViewModel::class.java)) MemoDetailViewModel(memoId, memoRepo) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}