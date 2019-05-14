package me.mo3in.kroid.http.models

import android.content.Context
import com.google.gson.Gson
import me.mo3in.kroid.commons.extensions.fromJson
import me.mo3in.kroid.commons.extensions.toast
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

	init {

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

	private var _errors: ResultErrors? = null

	override val errors: ResultErrors
		get() {
			if (_errors == null) {
				if (response?.errorBody() != null) {
					val errorBody = response.errorBody()!!.string()
					if (errorBody.isNotEmpty())
						_errors = Gson().fromJson<ResultErrors>(errorBody)
				} else
					_errors = ResultErrors()
			}
			return _errors!!
		}


	val message: String = if (response != null) response.message() else if (throwable!!.message != null) throwable.message!! else ""

	fun showErrorAsToast(context: Context, showNetworkErrors: Boolean = false) {
		var message = if (showNetworkErrors) message else ""

		if (errors.size > 0)
			message = errors.toSeparateRows()

		context.toast(message)
	}
}