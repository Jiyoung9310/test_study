package com.example.mytestapplication

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern


class LoginViewModel : ViewModel() {
    private val EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    val userID = MutableLiveData<String>().apply { postValue("") }
    val userPW = MutableLiveData<String>().apply { postValue("") }

    private val _loginEnable = MediatorLiveData<Boolean>()
    val loginEnable = _loginEnable

    private val _validMessage = MutableLiveData<Int>()
    val validMessage : LiveData<Int>
        get() = _validMessage

    private val _signinEvent = MutableLiveData<Boolean>()
    val signinEvent : LiveData<Boolean>
        get() = _signinEvent

    init {
        _loginEnable.addSource(userID) { _loginEnable.value = (userID.value ?: "").isNotEmpty() && (userPW.value ?: "").isNotEmpty() }
        _loginEnable.addSource(userPW) { _loginEnable.value = (userID.value ?: "").isNotEmpty() && (userPW.value ?: "").isNotEmpty() }
    }

    fun onClickLogin() {
        _validMessage.postValue(
            when {
                !isValidEmail(userID.value) -> R.string.valid_error_id_format
                (userPW.value?:"").length < 6 -> R.string.valid_error_pw_format
                else -> 0
            }
        )
    }

    fun onClickSignIn() {
        _signinEvent.postValue(true)
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        target?.let {
            return if (target.isEmpty()) {
                false
            } else {
                EMAIL_ADDRESS.matcher(target).matches()
            }
        }
        return false
    }
}
