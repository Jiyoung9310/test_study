package com.example.mytestapplication

import android.app.Application
import androidx.room.Room
import com.example.mytestapplication.memo.data.MemoDatabase
import com.facebook.stetho.Stetho

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}