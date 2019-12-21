package com.example.mytestapplication.memo

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.example.mytestapplication.memo.dao.MemoDaoTestImp
import com.example.mytestapplication.memo.dao.MemoEmptyDaoTestImp
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import com.example.mytestapplication.memo.database.FakeEmptyMemoDatabase
import com.example.mytestapplication.memo.database.FakeMemoDatabase
import com.example.mytestapplication.memo.ui.MemoFragment
import com.example.mytestapplication.memo.ui.MemoViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MemoViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : MemoViewModel

    @Before
    fun setUp() {
//        viewModel = MemoViewModel(MemoRepository.getInstance(MemoDaoTestImp()))
    }

    @Test
    fun `(Given) 앱 실행 시 (When) 메모가 없으면 (Then) 없음 메시지 표시`() {
        viewModel = MemoViewModel(MemoRepository.getInstance(FakeEmptyMemoDatabase()))
        val expectedResult = true
        viewModel.showEmptyMessage.observeForever{
            println("showEmptyMessage : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 시 (When) 메모가 있으면 (Then) 제목, 날짜 표시`() {
        viewModel = MemoViewModel(MemoRepository.getInstance(FakeMemoDatabase()))
        val expectedResult = arrayListOf(
            MemoTile("메모1", "2019.12.17"),
            MemoTile("메모2", "2019.12.17"),
            MemoTile("메모3", "2019.12.17")
        )
        viewModel.memoListData.observeForever{
            println("memoListData : ${it.peekContent()}")
            assertEquals(expectedResult, it.peekContent())
        }
    }
}