package me.mo3in.kroid.http

import me.mo3in.kroid.commons.services.KServiceConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * create an instance of retrofit interface for direct call
 */
object HttpClient {

    fun makeBuilder(url: String, client: OkHttpClient? = null): Retrofit.Builder {
        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())

        if (!url.isEmpty())
            builder.baseUrl(url)
        else
            builder.baseUrl(KServiceConfig.httpEndPoint)

        if (client != null)
            builder.client(client)

        return builder
    }

    inline fun <reified T> build(url: String = ""): T {
        return makeBuilder(url).build().create(T::class.java)
    }

    inline fun <reified T> buildRx(url: String = ""): T {
        val builder = makeBuilder(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

        return builder.build().create(T::class.java)
    }
}