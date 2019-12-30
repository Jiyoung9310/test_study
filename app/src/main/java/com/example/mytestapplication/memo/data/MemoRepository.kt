package com.example.mytestapplication.memo.data

/**
 * Created by jiyoung on 16/12/2019
 */
class MemoRepository private constructor(private val memoDatabase: MemoDatabase) {
    fun getMemoList() = memoDatabase.memoDao().getMemoList()

    fun getMemo(memoId: Long) = memoDatabase.memoDao().getMemo(memoId)

    fun addMemo(vararg datas: Memo) = memoDatabase.memoDao().upserts(*datas)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MemoRepository? = null

        fun getInstance(memoDatabase: MemoDatabase) =
            instance ?: synchronized(this) {
                instance ?: MemoRepository(memoDatabase).also { instance = it }
            }
    }
}