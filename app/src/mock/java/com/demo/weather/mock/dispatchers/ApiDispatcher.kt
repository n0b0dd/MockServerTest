package com.demo.weather.mock.dispatchers

import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ApiDispatcher(
    private val mockApis: Map<String, MockScenarios>
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            request.path?.startsWith(WEATHER_API_SEARCH_URL) == true ->
                SearchCityApiDispatcher().dispatch(request)
            request.path?.startsWith(WEATHER_API_CURRENT_WEATHER_URL) == true ->
                LocalWeatherApiDispatcher().dispatch(request)
            else ->
                MockResponse().setResponseCode(404)
        }
    }
}