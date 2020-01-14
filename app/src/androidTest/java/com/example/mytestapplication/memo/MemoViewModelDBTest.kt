package com.example.mytestapplication.memo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import com.example.mytestapplication.memo.ui.MemoViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MemoViewModelDBTest {

    private lateinit var memoDatabase: MemoDatabase
    private lateinit var viewModel : MemoViewModel

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        memoDatabase = Room.inMemoryDatabaseBuilder(context, MemoDatabase::class.java).build()
        val memoRepo = MemoRepository.getInstance(memoDatabase.memoDao())
        viewModel = MemoViewModel(memoRepo)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        memoDatabase.close()
    }

}