package com.demo.weather.api

import com.demo.weather.apimodel.LocalWeather
import com.demo.weather.model.City
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET(WEATHER_API_CURRENT_WEATHER_URL)
    fun currentWeather(
        @Query("key") key: String,
        @Query("q") query: String
    ): Flow<LocalWeather>

    @GET(WEATHER_API_SEARCH_URL)
    fun queryCities(
        @Query("key") key: String,
        @Query("q") query: String
    ): Flow<List<City>>


}