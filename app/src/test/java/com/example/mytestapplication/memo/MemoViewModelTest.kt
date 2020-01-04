package com.example.mytestapplication.memo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytestapplication.memo.data.Memo
import com.example.mytestapplication.memo.data.MemoDetailData
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import com.example.mytestapplication.memo.database.FakeEmptyMemoDatabase
import com.example.mytestapplication.memo.database.FakeMemoDatabase
import com.example.mytestapplication.memo.ui.MemoAddViewModel
import com.example.mytestapplication.memo.ui.MemoViewModel
import com.example.mytestapplication.memo.ui.MemoDetailViewModel
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
    private lateinit var addViewModel : MemoAddViewModel
    private lateinit var detailViewModel : MemoDetailViewModel

    @Before
    fun setUp() {
//        viewModel = MemoViewModel(MemoRepository.getInstance(MemoDaoTestImp()))
        val repo = MemoRepository.getInstance(FakeMemoDatabase())
        baseViewModel = MemoBaseViewModel(repo)
        addViewModel = MemoAddViewModel(repo)
        detailViewModel = MemoDetailViewModel(1, repo)
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
            MemoTile(1,"메모1", "2019.12.17"),
            MemoTile(2,"메모2", "2019.12.17"),
            MemoTile(3,"메모3", "2019.12.17")
        )
        viewModel.memoListData.observeForever{
            println("memoListData : $it")
            assertEquals(expectedResult, it)
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
            println("navigateAddEvent : $it")
            assertEquals(expectedResult, it)
        }
        viewModel.onClickFloatingButton()
    }

    @Test
    fun `(Given) 메모추가화면 진입 시 (When) x (Then) 완료버튼 비활성화`() {
        val expectedResult = false
        addViewModel.memoTitle.postValue("")
        addViewModel.memoCategory.postValue("")
        addViewModel.memoContents.postValue("")
        addViewModel.doneButtonEnable.observeForever {
            println("doneButtonEnable : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 메모 추가 화면에서 (When) 제목 입력"abc", 카테고리 미선택, 메모 미입력 시 (Then) 완료버튼 비활성화` () {
        val expectedResult = false
        addViewModel.memoTitle.postValue("abc")
        addViewModel.changeCategory(0)
        addViewModel.memoContents.postValue("")
        addViewModel.doneButtonEnable.observeForever {
            println("doneButtonEnable2 : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 메모 추가 화면에서 (When) 제목 입력 미입력, 카테고리 선택"아이디어", 메모 미입력 시 (Then) 완료버튼 비활성화` () {
        val expectedResult = false
        addViewModel.memoTitle.postValue("")
        addViewModel.changeCategory(2)
        addViewModel.memoContents.postValue("")
        addViewModel.doneButtonEnable.observeForever {
            println("doneButtonEnable2 : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 메모 추가 화면에서 (When) 제목 입력 입력"abc", 카테고리 선택"아이디어", 메모 미입력 시 (Then) 완료버튼 활성화` () {
        val expectedResult = true
        addViewModel.memoTitle.postValue("abc")
        addViewModel.changeCategory(2)
        addViewModel.memoContents.postValue("")
        addViewModel.doneButtonEnable.observeForever {
            println("doneButtonEnable3 : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 메모 추가 화면에서 (When) 메모 입력 시 (Then) 메모 글자수 표시` () {
        val expectedResult = 10
        addViewModel.memoContents.postValue("일이삼사오육칠팔구십")
        addViewModel.memoCounter.observeForever {
            println("memoCounter : $it")
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 메모 추가 화면에서 (When) 모든 정보 입력 후 완료 클릭  (Then) 메모저장, 상세화면으로 이동` () {
        val expectedResult = Memo(
            memoId = 0,
            title = "abc",
            category = "아이디어",
            description = "일이삼사오육칠팔구십"
        )
        addViewModel.memoTitle.postValue("abc")
        addViewModel.changeCategory(2)
        addViewModel.memoContents.postValue("일이삼사오육칠팔구십")
        addViewModel.doneButtonEnable.observeForever {
            println("doneButtonEnable : $it")
            if(it) addViewModel.onClickDone()
        }
        addViewModel.doneEvent.observeForever {
            addViewModel.saveMemo()
        }
        addViewModel.saveMemoEvent.observeForever {
            println("doneButtonEnable : $it")
            addViewModel.navigateDetailEvent(it)
        }
        addViewModel.navigateDetailEvent.observeForever {
            assertEquals(expectedResult.memoId, it)
        }
    }

    @Test
    fun `(Given) 메모 상세 화면 진입 시 (When) x (Then) 메모 데이터 받아오기`() {
        val expectedResult = MemoDetailData(
            title = "메모1",
            category = "기타",
            contents = "메모이다1"
        )
        detailViewModel.memoData.observeForever {
            println("memoData : $it")
            assertEquals(expectedResult, it)
        }
    }
}