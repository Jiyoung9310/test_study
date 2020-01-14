package com.example.mytestapplication.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.MemoAddViewModel
import com.example.mytestapplication.memo.ui.MemoDetailViewModel
import com.example.mytestapplication.memo.ui.MemoViewModel

class MemoBaseViewModelFactory(val memoRepo : MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MemoBaseViewModel::class.java) -> {
                MemoBaseViewModel(memoRepo) as T
            }
            modelClass.isAssignableFrom(MemoViewModel::class.java) -> {
                MemoViewModel(memoRepo) as T
            }
            modelClass.isAssignableFrom(MemoDetailViewModel::class.java) -> {
                MemoDetailViewModel(memoRepo) as T
            }
            modelClass.isAssignableFrom(MemoAddViewModel::class.java) -> {
                MemoAddViewModel(memoRepo) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}