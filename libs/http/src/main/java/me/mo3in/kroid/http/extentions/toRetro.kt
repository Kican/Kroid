package me.mo3in.kroid.http.extentions

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import me.mo3in.kroid.http.models.RetroResult
import retrofit2.HttpException
import retrofit2.Response

/**
 * convert to custom observable
 */
fun <T> Observable<Response<T>>.toRetro(): Observable<RetroResult<T>> {
    return map { RetroResult.fromData(it) }.onErrorResumeNext { t: Throwable -> Observable.just(RetroResult.fromError(t)) }
}

// execute when request is successful
fun <T> Observable<RetroResult<T>>.onlySuccess(): Observable<T> {
    return filter { it.isSuccessful }.map { it.data!! }
}

// execute when request is failed
fun <T> Observable<RetroResult<T>>.onlyError(): Observable<Throwable> {
    return filter { !it.isSuccessful }.map { it.throwable!! }
}

fun <T> Observable<RetroResult<T>>.onlyHttpException(): Observable<HttpException> {
    return filter { !it.isSuccessful && it.throwable is HttpException }.map { it.throwable as HttpException }
}

fun <T> Observable<RetroResult<T>>.onlyHttpException(code: Int): Observable<HttpException> {
    return onlyHttpException().filter { it.code() == code }
}

fun <T> Observable<RetroResult<T>>.onlyHttpExceptionExcluding(vararg codes: Int): Observable<HttpException> {
    return onlyHttpException().filter { codes.contains(it.code()) }
}