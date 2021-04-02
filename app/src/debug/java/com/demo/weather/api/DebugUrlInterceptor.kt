package com.demo.weather.api

import android.util.Log
import com.demo.weather.mock.mockserver.MockServerManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class DebugUrlInterceptor : Interceptor {
    private var mockServerManager: MockServerManager? = null

    fun setMockServerManger(manager: MockServerManager) {
        mockServerManager = manager
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (mockServerManager?.shouldMockApi(request.url.encodedPath) == true) {
            val newUrl: HttpUrl = request.url.newBuilder()
                .host(MockServerManager.HOST)
                .port(mockServerManager?.port ?: 0)
                .build()
            Log.i("DebugUrlInterceptor", "Mocking URL: ${newUrl.toString()}")
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}