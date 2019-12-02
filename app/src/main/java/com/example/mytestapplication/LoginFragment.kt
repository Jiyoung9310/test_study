package com.example.mytestapplication

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mytestapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var baseViewModel : BaseViewModel
    private lateinit var viewModel: LoginViewModel

    private lateinit var binding : FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseViewModel = ViewModelProviders.of(activity!!).get(BaseViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.vm = viewModel


        viewModel.loginEnable.observe(this, Observer {
            binding.btnLogin.isEnabled = it
        })

        viewModel.validMessage.observe(this, Observer {
            when(it) {
                0 -> baseViewModel.loginSuccess()
                else -> Toast.makeText(context, resources.getString(it), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.signinEvent.observe(this, Observer {
            if(it) baseViewModel.signInEvent()
        })
    }

}
