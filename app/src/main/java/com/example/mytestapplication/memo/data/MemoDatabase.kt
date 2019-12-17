package com.example.mytestapplication.memo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by jiyoung on 16/12/2019
 */
@Database(
    entities = [Memo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}