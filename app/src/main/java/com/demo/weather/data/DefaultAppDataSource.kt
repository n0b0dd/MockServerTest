package com.demo.weather.data

import com.demo.weather.api.AppService
import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.model.City
import com.demo.weather.util.WEATHER_API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit

class DefaultAppDataSource constructor(private val retrofit: Retrofit) : AppDataSource {

    private val TAG = DefaultAppDataSource::class.java.simpleName
    private val apiService = retrofit.create(AppService::class.java)

    override suspend fun currentWeather(query: String): Flow<CurrentCondition?> = apiService.currentWeather(WEATHER_API_KEY, query).map { it.current }

    override suspend fun queryCities(query: String): Flow<List<City>> = apiService.queryCities(WEATHER_API_KEY, query)

}