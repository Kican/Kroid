package me.mo3in.kroid.auth

import me.mo3in.kroid.auth.models.TokenResponse
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterResponse
import me.mo3in.kroid.commons.extensions.toJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class AuthTestServerDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path.toLowerCase()) {
            "/api/account/register" -> MockResponse().setResponseCode(500)
            "/api/account/Login" -> MockResponse().setResponseCode(500)
            "/api/account/PhoneRequest".toLowerCase() -> MockResponse().setResponseCode(200).setBody(PhoneRegisterResponse("100022", true).toJson())
            "/api/account/LoginOtp".toLowerCase() -> {
                MockResponse().setResponseCode(200)
                        .setBody(TokenResponse("abc.efg.hic", "Bearer", "abvcasd", 4500).toJson())
            }
            "/api/data/list" -> {

                val header = request.headers.get("Authorization")
                        ?: return MockResponse().setResponseCode(401)

                if (header.removePrefix("Bearer ").split(".").size != 3)
                    return MockResponse().setResponseCode(401)

                MockResponse().setResponseCode(200).setBody(
                        arrayOf(Data(1, "hi"), Data(2, "salam")).toJson()
                )
            }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

data class Data(val id: Int, val title: String)