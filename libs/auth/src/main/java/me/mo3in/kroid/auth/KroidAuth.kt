package me.mo3in.kroid.auth

import io.reactivex.Observable
import io.reactivex.Single
import me.mo3in.kroid.auth.models.BaseUserRegisterModel

object KroidAuth {

    fun isSignIn(): Boolean {
        return false
    }

//    fun <T> getUserInfo(): Observable<T> {
////        return Observable.just(T)
//    }

    fun <T : BaseUserRegisterModel> createUser(model: T) {

    }

    fun signInWithPhoneNumber(phone: String) {

    }

    fun signInWithEmailAndPassword(email: String, pass: String) {

    }

    fun signInWithToken(token: String): Single<Boolean> {

        return Single.just(true)
    }

    fun signOut() {
    }

    fun isInRole(role: String): Boolean {
        return false
    }
}