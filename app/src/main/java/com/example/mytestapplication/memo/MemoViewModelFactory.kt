package com.example.mytestapplication.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.MemoViewModel

class MemoViewModelFactory(val memoRepo : MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MemoViewModel::class.java)) MemoViewModel(memoRepo) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}