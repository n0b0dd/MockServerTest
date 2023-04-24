package com.demo.weather

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Log.d(">>>", "Dagger inject object initializing !!!")
    }

}