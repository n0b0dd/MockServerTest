package com.demo.weather.data

import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAppRepository @Inject constructor(private val dataSource: AppDataSource) : AppRepository {

    override suspend fun currentWeather(query: String): Flow<CurrentCondition?> = flow {
        dataSource.currentWeather(query)
    }

    override suspend fun queryCities(query: String): Flow<List<City>> = flow{
        dataSource.queryCities(query)
    }
}