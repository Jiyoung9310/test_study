package com.example.mytestapplication.memo.database

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.mytestapplication.memo.dao.MemoEmptyDaoTestImp
import com.example.mytestapplication.memo.data.MemoDao
import com.example.mytestapplication.memo.data.MemoDatabase
import org.mockito.Mockito

class FakeEmptyMemoDatabase : MemoDatabase() {
    override fun memoDao(): MemoDao {
        return MemoEmptyDaoTestImp()
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        return Mockito.mock(SupportSQLiteOpenHelper::class.java)
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return Mockito.mock(InvalidationTracker::class.java)
    }

    override fun clearAllTables() {}
}