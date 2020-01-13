package com.example.mytestapplication.memo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by jiyoung on 16/12/2019
 */
@Database(
    entities = [Memo::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private const val databaseName = "memo-db"

        fun buildDatabase(context: Context): MemoDatabase {
            // Since Room is only used for FTS, destructive migration is enough because the tables
            // are cleared every time the app launches.
            // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
            return Room.databaseBuilder(context, MemoDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}