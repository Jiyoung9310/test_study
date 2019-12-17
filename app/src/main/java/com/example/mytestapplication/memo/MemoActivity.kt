package com.example.mytestapplication.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.mytestapplication.R
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.ui.MemoFragment

class MemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MemoFragment.newInstance())
                .commitNow()
        }

    }

}
