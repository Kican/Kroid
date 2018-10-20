package me.mo3in.kroid.auth.extensions

import android.content.Context
import me.mo3in.kroid.auth.HttpBearerInterceptor
import me.mo3in.kroid.http.HttpClient
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

inline fun <reified T> HttpClient.authBuild(context: Context, url: String = ""): T {
    val httpClient = OkHttpClient.Builder().addInterceptor(HttpBearerInterceptor(context))

    val builder = makeBuilder(url, httpClient.build())

    return builder.build().create(T::class.java)
}

inline fun <reified T> HttpClient.authBuildRx(context: Context, url: String = ""): T {
    val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpBearerInterceptor(context))

    val builder = makeBuilder(url, httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

    return builder.build().create(T::class.java)
}