package me.mo3in.kroid.auth

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class HttpBearerInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = KroidAuth(context).getToken()

        val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        return chain.proceed(newRequest)
    }
}