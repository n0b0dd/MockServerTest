package com.demo.weather.data

import android.util.Log
import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.mock.dispatchers.ApiDispatcher
import com.demo.weather.mock.dispatchers.LocalWeatherApiDispatcher
import com.demo.weather.mock.dispatchers.SearchCityApiDispatcher
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.mock.mockserver.MockServerManager
import com.demo.weather.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class DefaultMockDataSource :AppDataSource {

    override suspend fun currentWeather(query: String): Flow<CurrentCondition?> = flow {
        Thread {
            val mockServer = MockWebServer()
            mockServer.dispatcher = LocalWeatherApiDispatcher()
            mockServer.start()
            Log.d(">>", "Started mock server at: ${mockServer.url("")}")
        }.start()
    }

    override suspend fun queryCities(query: String): Flow<List<City>> = flow {
        Thread {
            val mockServer = MockWebServer()
            mockServer.dispatcher = SearchCityApiDispatcher()
            mockServer.start()
            Log.d(">>", "Started mock server at: ${mockServer.url("")}")
        }.start()
    }
}