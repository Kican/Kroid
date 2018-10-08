package me.mo3in.kroid.auth

import io.reactivex.Single

object KroidAuth {

    fun isSignIn(): Boolean {
        return false
    }

    fun signInWithCredential(token: String): Single<Boolean> {

        return Single.just(true)
    }

    fun signOut() {
    }

    fun isInRole(role: String): Boolean {
        return false
    }
}