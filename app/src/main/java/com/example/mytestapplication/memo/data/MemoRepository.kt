package com.example.mytestapplication.memo.data

/**
 * Created by jiyoung on 16/12/2019
 */
class MemoRepository private constructor(private val memoDao: MemoDao) {
    fun getMemoList() = memoDao.getMemoList()

    fun getMemo(memoId: String) = memoDao.getMemo(memoId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MemoRepository? = null

        fun getInstance(memoDao: MemoDao) =
            instance ?: synchronized(this) {
                instance ?: MemoRepository(memoDao).also { instance = it }
            }
    }
}