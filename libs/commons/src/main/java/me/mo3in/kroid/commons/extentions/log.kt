package me.mo3in.kroid.commons.extentions

import android.util.Log
import com.google.gson.Gson

inline fun <reified T> log(tag: String, value: T) {
    val msg = when (T::class.java) {
        Int::class.java,
        String::class.java,
        Boolean::class.java,
        Long::class.java,
        Float::class.java -> value.toString()
        else -> Gson().toJson(value)
    }
    Log.i(tag, msg)
}

inline fun <reified T> log(value: T) {
    log("Kroid", value)
}

inline fun <reified T> log(vararg value: T) {
    value.forEach { t -> log(t) }
}

inline fun <reified T> log(tag: String, vararg value: T) {
    value.forEach { t -> log(tag, t) }
}
