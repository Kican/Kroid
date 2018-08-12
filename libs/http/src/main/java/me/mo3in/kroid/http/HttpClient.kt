package me.mo3in.kroid.http

import me.mo3in.kroid.commons.services.KServiceConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * create an instance of retrofit interface for direct call
 */
object HttpClient {
    inline fun <reified T> build(url: String = ""): T {
        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())

        if (!url.isEmpty())
            builder.baseUrl(url)
        else
            builder.baseUrl(KServiceConfig.httpEndPoint)


        return builder.build().create(T::class.java)
    }
}