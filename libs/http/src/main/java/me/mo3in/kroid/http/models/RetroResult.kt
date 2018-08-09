package me.mo3in.kroid.http.models

import android.content.Context
import com.google.gson.Gson
import me.mo3in.kroid.commons.extentions.fromJson
import me.mo3in.kroid.commons.extentions.toast
import me.mo3in.kroid.commons.models.DataResult
import me.mo3in.kroid.commons.models.ResultErrors
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class RetroResult<T>(val response: Response<T>? = null, throwable: Throwable?) : DataResult<T>(throwable = throwable) {
    companion object {
        fun <T> fromData(data: Response<T>): RetroResult<T> {
            return RetroResult(data, null)
        }

        fun <T> fromError(error: Throwable): RetroResult<T> {
            return RetroResult(null, error)
        }
    }

    override val data: T?
        get() {
            if (isSuccessful)
                return response!!.body()
            return null
        }

    val statusCode: Int
        get() {
            if (response != null)
                return response.code()
            return 0
        }

    val isSuccessful: Boolean
        get() {
            if (response != null && throwable == null && response.isSuccessful)
                return true
            return false
        }

    var errorType: ResponseErrorType = if (!isSuccessful) {
        if (response != null) {
            when (response.code()) {
                400 -> ResponseErrorType.BadRequestError
                401, 403 -> ResponseErrorType.UnAuthorizeError
                else -> ResponseErrorType.ServerError
            }
        } else
            when (throwable) {
                is SocketTimeoutException -> ResponseErrorType.TimeOutError
                is IOException -> ResponseErrorType.NetworkError
                else -> ResponseErrorType.UnknownError
            }
    } else
        ResponseErrorType.Nothing

    override val errors: ResultErrors
        get() {
            if (response?.errorBody() != null) {
                val errorBody = response.errorBody()!!.string()
                if (errorBody.isNotEmpty())
                    return Gson().fromJson<ResultErrors>(errorBody)
            }
            return ResultErrors()
        }


    val message: String = if (response != null) response.message() else if (throwable!!.message != null) throwable.message!! else ""

    fun showErrorAsToast(context: Context) {
        var message = message
        if (errorType == ResponseErrorType.BadRequestError && errors.size > 0)
            message = errors.toSeparateRows()

        context.toast(message)
    }
}