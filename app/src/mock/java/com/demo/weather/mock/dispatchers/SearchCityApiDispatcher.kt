package com.demo.weather.mock.dispatchers

import com.demo.weather.mock.MockUtils
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.ResourceUtils
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SearchCityApiDispatcher(
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            request.path?.startsWith(WEATHER_API_SEARCH_URL) == true -> {
                MockUtils.success("search_api_success.json")
            }
//            MockScenarios.SUCCESS ->
//                MockUtils.success("search_api_success.json")
//            MockScenarios.FAILURE ->
//                MockUtils.failure(500)
            else ->
                MockUtils.default
        }
    }
}