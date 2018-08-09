package me.mo3in.kroid.http

import me.mo3in.kroid.commons.services.KServiceConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient {
    companion object {
        inline fun <reified T> build(basePath: String = ""): T {
            val builder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())

            if (!basePath.isEmpty())
                builder.baseUrl(basePath)
            else
                builder.baseUrl(KServiceConfig.httpEndPoint)

            return builder.build().create(T::class.java)
        }

        inline fun <reified T> buildRx(basePath: String = ""): T {
            val builder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

            if (!basePath.isEmpty())
                builder.baseUrl(basePath)
            else
                builder.baseUrl(KServiceConfig.httpEndPoint)

            return builder.build().create(T::class.java)
        }
    }
}