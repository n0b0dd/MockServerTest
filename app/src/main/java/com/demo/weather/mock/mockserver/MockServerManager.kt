package com.demo.weather.mock.mockserver

import android.util.Log
import com.demo.weather.mock.dispatchers.ApiDispatcher
import okhttp3.mockwebserver.MockWebServer

class MockServerManager {
    var port = 0
    private val mockApis = mutableMapOf<String, MockScenarios>()
    private var mockServer: MockWebServer? = null
    private var isServerStarted = false

    fun startServer() {
        if (!isServerStarted) {
            Thread {
                mockServer = MockWebServer()
                mockServer!!.dispatcher = ApiDispatcher(mockApis)
                mockServer!!.start()
                port = mockServer!!.port
                Log.d(TAG, "Started mock server at: ${mockServer!!.url("")}")
                isServerStarted = true
            }.start()
        }
    }

    fun stopServer() {
        isServerStarted = false
        mockServer?.shutdown()
    }

    fun enableApi(api: String, scenarios: MockScenarios) {
        mockApis[api] = scenarios
    }

    fun disableApi(api: String) {
        if (mockApis.contains(api)) {
            mockApis.remove(api)
        }
    }

    fun shouldMockApi(api: String): Boolean {
        mockApis.forEach {
            if (api.contains(it.key)) {
                return true
            }
        }
        return false
    }

    companion object {
        private const val TAG = "MockServerManager"
        const val HOST = "localhost"
    }
}