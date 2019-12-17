package com.example.mytestapplication

import android.app.Application
import androidx.room.Room
import com.example.mytestapplication.memo.data.MemoDatabase

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}