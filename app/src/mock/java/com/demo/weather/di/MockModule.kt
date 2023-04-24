package com.demo.weather.di

import com.demo.weather.data.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MockModule {

//    @Provides
//    @Singleton
//    fun provideRepository(dataSource: AppDataSource) : AppRepository = DefaultAppRepository(dataSource)
//
//    @Provides
//    @Singleton
//    fun provideDataSource() : AppDataSource = DefaultMockDataSource()

    @Binds
    abstract fun bindRepository(
        mockRepo: DefaultAppRepository
    ) : AppRepository

}