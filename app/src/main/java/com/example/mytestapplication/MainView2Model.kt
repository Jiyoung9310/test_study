package com.example.mytestapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainView2Model : ViewModel() {
    val TAG = this::class.java.simpleName

    val plus = "+"
    val minus = "-"
    val times = "*"
    val div = "/"

    val firstNumber = MutableLiveData<String>().apply { postValue("0") }
    val secondNumber = MutableLiveData<String>().apply { postValue("0") }

    private val _operatorEvent = MutableLiveData<String>().apply { postValue(plus) }
    val operatorEvent : LiveData<String> get() = _operatorEvent

    private val _resultData = Transformations.map(operatorEvent) {
        val first = firstNumber.value?.toInt() ?: 0
        val second = secondNumber.value?.toInt() ?: 0
        when(it) {
            "+" -> first.plus(second)
            "-" -> first.minus(second)
            "*" -> first.times(second)
            "/" -> first.div(second)
            else -> 0
        }.toString()
    }
    val resultData : LiveData<String> get() = _resultData

    fun onClickOperator(oper: String) {
        _operatorEvent.postValue(oper)
    }

}