package com.example.mytestapplication.memo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.data.MemoTile
import com.example.mytestapplicaton.memo.ui.MemoViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MemoViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var memoDatabase: MemoDatabase
    @Before
    fun setUp() {
        memoDatabase = Room.inMemoryDatabaseBuilder(context, MemoDatabase::class.java).build()
        val plantRepo = PlantRepository.getInstance(appDatabase.plantDao())
        val gardenPlantingRepo = GardenPlantingRepository.getInstance(
            appDatabase.gardenPlantingDao())
        viewModel = PlantDetailViewModel(plantRepo, gardenPlantingRepo, testPlant.plantId)
    }

    @Test
    fun `(Given) 앱 실행 시 (When) 메모가 없으면 (Then) 없음 메시지 표시`() {
        val expectedResult = true
        val viewModel = MemoViewModel(MemoRepository())
        viewModel.memoListData.observeForever{
            viewModel.isListEmpty(it.isEmpty)
        }
        viewModel.showEmptyMessage.observeForever{
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(Given) 앱 실행 시 (When) 메모가 있으면 (Then) 제목, 날짜 표시`() {
        val expectedResult = arrayListOf(
            MemoTile("메모1", "2019.12.01"),
            MemoTile("메모2", "2019.12.02"),
            MemoTile("메모3", "2019.12.03")
        )
        val viewModel = MemoViewModel()
        viewModel.memoListData.observeForever{
            assertEquals(expectedResult, it)
        }
    }
}