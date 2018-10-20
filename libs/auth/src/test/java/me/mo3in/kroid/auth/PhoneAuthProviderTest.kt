package me.mo3in.kroid.auth

import me.mo3in.kroid.auth.providers.phone.PhoneAuthProvider
import me.mo3in.kroid.commons.extensions.toJson
import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.setHttpEndPoint
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference

@RunWith(MockitoJUnitRunner::class)
class PhoneAuthProviderTest {
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
    fun requestCode() {
        val latch = CountDownLatch(1)
        val responseRef = AtomicReference<Boolean>()

        PhoneAuthProvider().requestOtp("+98000000000").subscribe { t ->
            responseRef.set(t.isSuccess() && t.data != null)
            latch.countDown()
        }

        latch.await()
        Assert.assertTrue(responseRef.get())
    }

    @Test
    fun login() {
        val latch = CountDownLatch(1)
        val responseRef = AtomicReference<Boolean>()

        PhoneAuthProvider().verifyPhoneNumber("+98000000000","559706").subscribe { t ->
            responseRef.set(t.isSuccess() && t.data != null && t.data!!.access_token.split('.').count() == 3)
            latch.countDown()
        }

        latch.await()
        Assert.assertTrue(responseRef.get())
    }

}