package me.mo3in.kroid.http.rx

import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.HttpClient
import me.mo3in.kroid.http.httpEndPoint
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> HttpClient.buildRx(url: String = ""): T {
    val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

    if (!url.isEmpty())
        builder.baseUrl(url)
    else
        builder.baseUrl(KServiceConfig.httpEndPoint)

    return builder.build().create(T::class.java)
}