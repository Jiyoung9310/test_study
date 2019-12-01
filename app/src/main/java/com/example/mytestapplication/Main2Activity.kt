package com.example.mytestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mytestapplication.databinding.ActivityMain2Binding

class Main2Activity : AppCompatActivity() {

    private val viewModelFactory = MainView2ModelFactory()
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainView2Model::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
        val binding = DataBindingUtil.setContentView<ActivityMain2Binding>(this, R.layout.activity_main2).apply {
            vm = viewModel
        }

        binding.run {
            tvPlus.setOnClickListener { viewModel.onClickOperator("+") }
            tvMinus.setOnClickListener { viewModel.onClickOperator("-") }
            tvMulti.setOnClickListener { viewModel.onClickOperator("*") }
            tvDivision.setOnClickListener { viewModel.onClickOperator("/") }
        }

        viewModel.resultData.observe(this, Observer {
            binding.tvResult.text = String.format(getString(R.string.result), it)
        })

        viewModel.operatorEvent.observe(this, Observer {
            binding.tvOperator.text = it
        })

    }
}
