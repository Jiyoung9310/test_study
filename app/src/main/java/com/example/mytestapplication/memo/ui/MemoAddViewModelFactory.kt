package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.memo.data.MemoRepository

class MemoAddViewModelFactory(val memoRepo : MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MemoAddViewModel::class.java)) MemoAddViewModel(memoRepo) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}