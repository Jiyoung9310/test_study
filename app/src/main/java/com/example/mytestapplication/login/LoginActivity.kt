package com.example.mytestapplication.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mytestapplication.R


/**
 * 로그인 화면 : LoginFragment
 * 로그인 후 화면 : HomeFragment
 * 회원가입 화면 : SignInFragment
 */
class LoginActivity : AppCompatActivity() {

    private val viewModelFactory = LoginModelFactory()
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        BaseViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                LoginFragment()
            ).commitNow()
        }

        viewModel.homeEvent.observe(this, Observer {
            if(it) supportFragmentManager.beginTransaction().replace(
                R.id.container,
                HomeFragment()
            ).commitNow()
        })

        viewModel.signinEvent.observe(this, Observer {
            if(it) supportFragmentManager.beginTransaction().replace(
                R.id.container,
                SignInFragment()
            ).commitNow()
        })
    }
}
