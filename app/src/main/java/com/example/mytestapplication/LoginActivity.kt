package com.example.mytestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class LoginActivity : AppCompatActivity() {

    private val viewModelFactory = LoginModelFactory()
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(BaseViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).commitNow()
        }

        viewModel.homeEvent.observe(this, Observer {
            if(it) supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commitNow()
        })

        viewModel.signinEvent.observe(this, Observer {
            if(it) supportFragmentManager.beginTransaction().replace(R.id.container, SignInFragment()).commitNow()
        })
    }
}
