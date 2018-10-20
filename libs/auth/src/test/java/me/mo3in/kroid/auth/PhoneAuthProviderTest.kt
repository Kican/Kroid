package me.mo3in.kroid.auth

import android.support.test.runner.AndroidJUnit4
import me.mo3in.kroid.auth.providers.phone.PhoneAuthProvider
import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.setHttpEndPoint
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper
import java.util.concurrent.atomic.AtomicReference


@RunWith(AndroidJUnit4::class)
@Config(sdk = [23, 28])
class PhoneAuthProviderTest {
    private val webServer: MockWebServer = MockWebServer()

    @Before
    fun setup() {
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
        val responseRef = AtomicReference<Boolean>(false)

        PhoneAuthProvider().requestOtp("+98000000000").subscribe { t ->
            responseRef.set(t.isSuccess() && t.data != null)
        }

        while (!responseRef.get()) {
            Thread.sleep(100);
            ShadowLooper.runUiThreadTasks()
        }
        Assert.assertTrue(responseRef.get())
    }

    @Test
    fun login() {
        val responseRef = AtomicReference<Boolean>(false)


        PhoneAuthProvider().verifyPhoneNumber(RuntimeEnvironment.application, "+98000000000", "559706").subscribe { t ->
            responseRef.set(t.isSuccess() && t.data != null && t.data!!.access_token.split('.').count() == 3)
        }

        while (!responseRef.get()) {
            Thread.sleep(100);
            ShadowLooper.runUiThreadTasks()
        }

        Assert.assertTrue(responseRef.get())
    }

}
