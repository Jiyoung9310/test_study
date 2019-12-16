package com.example.mytestapplication.memo.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by jiyoung on 16/12/2019
 */
internal abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upserts(vararg entities: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(entity: T)

    @Delete
    abstract fun delete(entity: T)

    @Delete
    abstract fun deletes(vararg entities: T)
}