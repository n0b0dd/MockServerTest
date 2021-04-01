package com.demo.weather.mock

import com.demo.weather.util.ResourceUtils
import okhttp3.mockwebserver.MockResponse

object MockUtils {
    val default = failure(404)

    fun success(file: String) =
        MockResponse().setResponseCode(200).setBody(
            ResourceUtils.getJsonString("json/$file")
        )

    fun failure(code: Int) =
        MockResponse().setResponseCode(code)
}