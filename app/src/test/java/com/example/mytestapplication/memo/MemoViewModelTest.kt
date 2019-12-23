package com.example.mytestapplication.memo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import com.example.mytestapplication.memo.database.FakeEmptyMemoDatabase
import com.example.mytestapplication.memo.database.FakeMemoDatabase
import com.example.mytestapplication.memo.ui.MemoViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MemoViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : MemoViewModel
    private lateinit var baseViewModel : MemoBaseViewModel

    @Before
    fun setUp() {
//        viewModel = MemoViewModel(MemoRepository.getInstance(MemoDaoTestImp()))
        baseViewModel = MemoBaseViewModel(MemoRepository.getInstance(FakeMemoDatabase()))
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
            MemoTile("1","메모1", "2019.12.17"),
            MemoTile("2","메모2", "2019.12.17"),
            MemoTile("3","메모3", "2019.12.17")
        )
        viewModel.memoListData.observeForever{
            println("memoListData : ${it.peekContent()}")
            assertEquals(expectedResult, it.peekContent())
        }
    }

    @Test
    fun `(Given) 홈 화면에서 (When) +버튼 누르면 (Then) 메모추가 화면으로 이동`() {
        viewModel = MemoViewModel(baseViewModel.memoRepo)
        val expectedResult = true
        viewModel.floatingButtonEvent.observeForever {
            println("floatingButtonEvent : ${it.peekContent()}")
            baseViewModel.navigateAddEvent()
        }
        baseViewModel.navigateAddEvent.observeForever {
            println("navigateAddEvent : ${it.peekContent()}")
            assertEquals(expectedResult, it.peekContent())
        }
        viewModel.onClickFloatingButton()
    }
}