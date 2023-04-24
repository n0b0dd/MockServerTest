package com.demo.weather.data

import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.model.City
import kotlinx.coroutines.flow.Flow

interface AppDataSource {

    suspend fun currentWeather(query: String): Flow<CurrentCondition?>

    suspend fun queryCities(query: String): Flow<List<City>>

}