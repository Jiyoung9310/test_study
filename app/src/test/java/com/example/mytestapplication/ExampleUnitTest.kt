package com.example.mytestapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytestapplication.login.BaseViewModel
import com.example.mytestapplication.login.LoginViewModel
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
    private lateinit var loginVM : LoginViewModel
    private lateinit var baseVM : BaseViewModel


    @Before
    fun before() {
        mainVM = MainViewModel(MockWeatherRepo())
        mainVM2 = MainView2Model()
        loginVM = LoginViewModel()
        baseVM = BaseViewModel()
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

    @Test
    fun `(given) ID or PW empty (when) x (then) 로그인 버튼 비활성화`() {
        val expectedResult = false
        loginVM.userID.postValue("1312")
        loginVM.userPW.postValue("")
        loginVM.loginEnable.observeForever {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(given) 형식이 다른 ID 입력 (when) 로그인 버튼 클릭 시 (then) 에러토스트 메시지`() {
        val expectedResult = R.string.valid_error_id_format
        loginVM.userID.postValue("dkdlel298")
        loginVM.onClickLogin()
        loginVM.validMessage.observeForever {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(given) 형식이 다른 PW 입력 (when) 로그인 버튼 클릭 시 (then) 에러토스트 메시지`() {
        val expectedResult = R.string.valid_error_pw_format
        loginVM.userID.postValue("dkdlel298@naver.com")
        loginVM.userPW.postValue("1234")
        loginVM.onClickLogin()
        loginVM.validMessage.observeForever {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(given) 형식이 올바른 ID, PW 입력 (when) 로그인 버튼 클릭 시 (then) HomeFragment 이동`() {
        val expectedResult = true
        loginVM.userID.postValue("dkdlel298@naver.com")
        loginVM.userPW.postValue("12341234")
        loginVM.onClickLogin()
        loginVM.validMessage.observeForever {
            when(it) {
                0 -> baseVM.loginSuccess()
            }
        }
        baseVM.homeEvent.observeForever {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun `(when) 회원가입 클릭 시 (then) SignIn Fragment 로 전환`() {
        val expectedResult = true
        loginVM.onClickSignIn()
        loginVM.signinEvent.observeForever {
            if(it) baseVM.signInEvent()
        }
        baseVM.signinEvent.observeForever {
            assertEquals(expectedResult, it)
        }
    }
}
