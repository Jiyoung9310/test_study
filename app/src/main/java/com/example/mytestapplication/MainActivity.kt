package com.example.mytestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mytestapplication.network.ApiClient
import com.example.mytestapplication.network.WeatherRepoImp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = this::class.java.simpleName

    private val viewModelFactory = MainViewModelFactory(WeatherRepoImp(ApiClient.client))
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObserver()

        /*btnHello.setOnClickListener {
//            viewModel.onClickHello()
            viewModel.reqWeatherData()
        }*/

    }

    private fun setObserver() {

        /*viewModel.helloEvent.observe(this, Observer {
            tvHello.text = it
        })*/

        viewModel.weatherEvent.observe(this, Observer {
            it.forEach { x ->
                val linear = LayoutInflater.from(this).inflate(R.layout.list_custom_item, null) as LinearLayout
                val date = linear.findViewById<TextView>(R.id.tvDate)
                val weather = linear.findViewById<TextView>(R.id.tvWeather)
                weather.text = x.weather[0].description
                date.text = x.dt.toString()
                Log.d(TAG, "weatherData : dt ${x.dt}")
                llList.addView(linear)
            }
        })

        viewModel.errorEvent.observe(this, Observer {
            print(it)
        })
    }

}
