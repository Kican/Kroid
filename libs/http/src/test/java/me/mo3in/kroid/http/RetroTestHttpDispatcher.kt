package me.mo3in.kroid.http

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RetroTestHttpDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path.toString()) {
            "/data" -> {
                MockResponse().setBody("[{title:'salam',value:10}]").setResponseCode(200)
            }
            "/error403" -> MockResponse().setResponseCode(403)
            "/error401" -> MockResponse().setResponseCode(401)
            "/error400" -> MockResponse()
                    .setBody("{\"\":[\"\",\"\",\"\"]},\"name\":[\"required\",]")
                    .setResponseCode(400)
            else -> MockResponse().setResponseCode(404)
        }

    }
}