package com.example.mytestapplication.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.mytestapplication.R
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.MemoFragment

class MemoActivity : AppCompatActivity() {

    private lateinit var database: MemoDatabase

    private lateinit var baseViewModelFactory : MemoBaseViewModelFactory
    private lateinit var viewModel : MemoBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        database = MemoDatabase.buildDatabase(this)
        baseViewModelFactory = MemoBaseViewModelFactory(MemoRepository.getInstance(database))
        viewModel = ViewModelProviders.of(this, baseViewModelFactory).get(MemoBaseViewModel::class.java)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MemoFragment.newInstance())
                .commitNow()
        }

    }

}
