package com.example.mytestapplication

import Weather
import X
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


    @Before
    fun before() {
        mainVM = MainViewModel(MockWeatherRepo())

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
        val result = arrayListOf<X>(
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow"))),
            X(weather = arrayListOf<Weather>(Weather(description = "sky is clear"))),
            X(weather = arrayListOf<Weather>(Weather(description = "light snow")))
        )
        mainVM.weatherEvent.observeForever {
            assertEquals(7, it.size)
            it.forEachIndexed { index, x ->
                assertEquals(result[index], x)
            }
        }
    }
}
