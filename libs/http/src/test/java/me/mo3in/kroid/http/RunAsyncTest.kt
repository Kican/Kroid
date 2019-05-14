package me.mo3in.kroid.http

import me.mo3in.kroid.commons.extensions.toJson
import me.mo3in.kroid.commons.models.Result
import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.extensions.runAsync
import me.mo3in.kroid.http.models.ResponseErrorType
import me.mo3in.kroid.http.models.RetroResult
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.http.GET
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference


@RunWith(MockitoJUnitRunner::class)
class RunAsyncTest {
	private lateinit var webServer: MockWebServer

	@Before
	fun setup() {
		webServer = MockWebServer()
		webServer.setDispatcher(RetroTestHttpDispatcher())
		webServer.start(8880)
		KServiceConfig.setHttpEndPoint("http://localhost:8880/")
	}

	@After
	fun tearDown() {
		webServer.shutdown()
	}

	@Test
	fun get_async_data_successful() {
		val latch = CountDownLatch(1)
		val responseRef = AtomicReference<Array<TestData>>()

		HttpClient.build<TestApiService>().getData().runAsync { retroResult ->
			responseRef.set(retroResult.data)
			latch.countDown()
		}

		latch.await()
		Assert.assertTrue(responseRef.get().size == 2)
	}

	@Test
	fun get_async_data_forbid() {
		val latch = CountDownLatch(1)
		val responseRef = AtomicReference<Boolean>(false)

		HttpClient.build<TestApiService>().forbid().runAsync { retroResult ->
			if (retroResult.errorType == ResponseErrorType.UnAuthorizeError)
				responseRef.set(true)
			latch.countDown()
		}

		latch.await()
		Assert.assertTrue(responseRef.get())
	}

	@Test
	fun get_async_data_badrequest() {
		val latch = CountDownLatch(1)
		val responseRef = AtomicReference<Boolean>(false)
		val resultRef = AtomicReference<RetroResult<Array<TestData>>>()

		HttpClient.build<TestApiService>().badRequest().runAsync { retroResult ->
			System.out.println(retroResult.errors.toJson())
			responseRef.set(true)
			resultRef.set(retroResult)

			latch.countDown()
		}

		latch.await()
		System.out.println(resultRef.get().errors.toJson())
		Assert.assertTrue(responseRef.get() && resultRef.get().errorType == ResponseErrorType.BadRequestError && resultRef.get().errors[""]!!.contains("field-is-required"))

	}

	interface TestApiService {
		@GET("/data")
		fun getData(): Call<Array<TestData>>

		@GET("/error403")
		fun forbid(): Call<Array<TestData>>

		@GET("/error400")
		fun badRequest(): Call<Array<TestData>>
	}

	data class TestData(val title: String, val id: Int)
}