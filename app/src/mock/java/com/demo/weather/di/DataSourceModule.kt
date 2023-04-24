package com.demo.weather.di

import android.util.Log
import com.demo.weather.data.AppDataSource
import com.demo.weather.data.DefaultMockDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object DataSourceModule {
//
//    @Provides
//    fun provideDataSource() : AppDataSource{
//        Log.d(">>>>", "provideDataSource: for Testing")
//        return DefaultMockDataSource()
//    }
//
//}