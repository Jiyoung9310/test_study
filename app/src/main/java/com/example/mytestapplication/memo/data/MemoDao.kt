package com.example.mytestapplication.memo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by jiyoung on 16/12/2019
 */
@Dao
interface MemoDao {
    @Query("SELECT * FROM memo ORDER BY writeDt")
    fun getMemoList(): LiveData<List<Memo>>

    @Query("SELECT * FROM memo WHERE id = :memoId")
    fun getMemo(memoId: Long): LiveData<Memo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upserts(vararg entities: Memo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: Memo): Long
}