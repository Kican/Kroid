package me.mo3in.kroid.auth.providers.phone

import android.app.Activity
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import me.mo3in.kroid.auth.providers.AuthProvider
import me.mo3in.kroid.auth.providers.emailpass.EmailPassApiService
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterResponse
import me.mo3in.kroid.commons.models.DataResult
import me.mo3in.kroid.http.HttpClient
import me.mo3in.kroid.http.extensions.runAsync

class PhoneAuthProvider : AuthProvider {
    override val providerId: String = "phone"

    fun requestOtp(phoneNumber: String, act: Activity): Single<DataResult<PhoneRegisterResponse>> {
        val result = SingleSubject.create<DataResult<PhoneRegisterResponse>>()

        HttpClient.build<EmailPassApiService>().requestOtp(PhoneRegisterRequest(phoneNumber)).runAsync { response ->
            result.onSuccess(response)
        }

        return result
    }

    fun verifyPhoneNumber(phoneNumber: String, code: String) {
    }
}