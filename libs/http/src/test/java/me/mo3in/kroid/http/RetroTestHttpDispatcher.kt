package me.mo3in.kroid.http

import me.mo3in.kroid.commons.extensions.toJson
import me.mo3in.kroid.commons.models.Result
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RetroTestHttpDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path.toString()) {
            "/data" -> {
                MockResponse().setBody("[{\"title\":\"salam\",\"id\":10}," +
                        "{\"title\":\"alo ...\",\"id\":8}]").setResponseCode(200)
            }
            "/error403" -> MockResponse().setResponseCode(403)
            "/error401" -> MockResponse().setResponseCode(401)
            "/error400" -> {
                val result = Result()
                result.errors!![""] = arrayOf("field-is-required")
                result.errors!!["user"] = arrayOf("is-duplicate", "not-exist")
                MockResponse()
                        .setBody(result.errors!!.toJson())
                        .setResponseCode(400)
            }
            else -> MockResponse().setResponseCode(404)
        }

    }
}