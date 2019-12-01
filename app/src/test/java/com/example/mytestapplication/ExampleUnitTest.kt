package com.example.mytestapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytestapplication.network.MockWeatherRepo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private val BASE_URL = "https://samples.openweathermap.org/"

    private lateinit var mainVM : MainViewModel
    private lateinit var mainVM2 : MainView2Model


    @Before
    fun before() {
        mainVM = MainViewModel(MockWeatherRepo())
        mainVM2 = MainView2Model()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun clickButton() {
        var result = ""
        mainVM.helloEvent.observeForever { result = it }
//        assertEquals("Hello World!", result)
        mainVM.onClickHello()
    }

    @Test
    fun getWeather() {
        /*val result = arrayListOf<X>(
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "sky is clear"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "light snow"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "light snow"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "sky is clear"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "light snow"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "sky is clear"))),
            X(weather = arrayListOf<com.example.mytestapplication.data.Weather>(com.example.mytestapplication.data.Weather(description = "light snow")))
        )
        mainVM.weatherEvent.observeForever {
            assertEquals(7, it.size)
            it.forEachIndexed { index, x ->
                assertEquals(result[index], x)
            }
        }*/
    }

    @Test
    fun `(when) 연산 버튼이 눌리면, (then) 결과가 바뀜 (to) 연산자에 맞게`() {

        val expectedResult = "10"
        mainVM2.firstNumber.postValue("2")
        mainVM2.secondNumber.postValue("5")
        mainVM2.onClickOperator("*")
        mainVM2.resultData.observeForever {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(when) 연산할 숫자가 바뀌면, (then) 결과가 바뀜`() {

    }
}
