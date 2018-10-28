package me.mo3in.kroid.auth

import androidx.test.runner.AndroidJUnit4
import me.mo3in.kroid.auth.extensions.authBuild
import me.mo3in.kroid.commons.extensions.defaultSharedPreferences
import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.HttpClient
import me.mo3in.kroid.http.extensions.runAsync
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
import retrofit2.Call
import retrofit2.http.GET
import java.util.concurrent.atomic.AtomicReference

@RunWith(AndroidJUnit4::class)
@Config(sdk = [23, 28])
class AuthHttpTests {
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
    fun getAuthData() {
        val responseRef = AtomicReference<Boolean?>(null)

        val context = RuntimeEnvironment.application


        context.defaultSharedPreferences.edit().putString("token", "abc.def.ghi").apply()

        HttpClient.authBuild<DataApiService>(context).getData().runAsync { t ->
            responseRef.set(t.isSuccessful && t.data!!.size == 2)
        }

        while (responseRef.get() == null) {
            Thread.sleep(100)
            ShadowLooper.runUiThreadTasks()
        }

        Assert.assertTrue(responseRef.get() == true)
    }
}

public interface DataApiService {
    @GET("/api/data/list")
    fun getData(): Call<Array<Data>>
}