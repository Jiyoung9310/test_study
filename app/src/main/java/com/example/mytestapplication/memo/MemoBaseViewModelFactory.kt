package com.example.mytestapplication.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.memo.data.MemoRepository

class MemoBaseViewModelFactory(val memoRepo : MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MemoBaseViewModel::class.java)) MemoBaseViewModel(memoRepo) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}