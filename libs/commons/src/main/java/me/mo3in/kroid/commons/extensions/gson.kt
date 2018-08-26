package me.mo3in.kroid.commons.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Any.toJson(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.to(): T {
    return Gson().fromJson<T>(this)
}