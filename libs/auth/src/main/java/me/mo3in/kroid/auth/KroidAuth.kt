package me.mo3in.kroid.auth

import android.content.Context
import io.reactivex.Single
import me.mo3in.kroid.commons.extensions.defaultSharedPreferences

class KroidAuth(val context: Context) {
    val TOKEN_KEY = "token"

    fun isSignIn(): Boolean {
        return context.defaultSharedPreferences.contains(TOKEN_KEY)
    }

    fun signInWithCredential(token: String): Single<Boolean> {
        context.defaultSharedPreferences.edit().putString(TOKEN_KEY, token).apply()
        return Single.just(true)
    }

    fun getToken(): String {
        return context.defaultSharedPreferences.getString(TOKEN_KEY, "")!!
    }

    fun signOut() {
        context.defaultSharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    fun isInRole(role: String): Boolean {
        return false
    }
}