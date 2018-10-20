package me.mo3in.kroid.auth

import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.setHttpEndPoint
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthHttpTests {
    private lateinit var webServer: MockWebServer

    @Before
    fun setup() {
        webServer = MockWebServer()
        webServer.setDispatcher(AuthTestServerDispatcher())
        webServer.start(8880)
        KServiceConfig.setHttpEndPoint("http://localhost:8880/")
        KServiceConfig.setAuthConfig(AuthConfig.Kasp("http://localhost:8880/"))
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun getAuthData() {

    }
}