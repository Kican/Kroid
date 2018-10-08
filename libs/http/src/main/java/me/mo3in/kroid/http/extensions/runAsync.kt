package me.mo3in.kroid.http.extensions

import me.mo3in.kroid.http.models.RetroResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.runAsync(result: (RetroResult<T>) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>) {
            result(RetroResult.fromData(response))
        }

        override fun onFailure(call: Call<T>?, t: Throwable) {
            result(RetroResult.fromError(t))
        }
    })
}
