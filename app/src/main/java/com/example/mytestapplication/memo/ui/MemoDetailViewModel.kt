package com.example.mytestapplication.memo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDetailData
import com.example.mytestapplication.memo.data.MemoRepository

class MemoDetailViewModel(val memoId : Long, val memoRepo : MemoRepository) : ViewModel() {

    private val _memoData = memoRepo.getMemo(memoId)?.let {
        Transformations.map(it) { memo ->
            MemoDetailData(
                title = memo.title,
                category = memo.category,
                contents = if(memo.description.isNotEmpty()) memo.description else "입력된 내용이 없습니다."
            )
        }
    } ?: MutableLiveData<MemoDetailData>()

    val memoData : LiveData<MemoDetailData> get() = _memoData


}
