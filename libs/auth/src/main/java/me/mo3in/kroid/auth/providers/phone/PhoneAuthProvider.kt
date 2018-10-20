package me.mo3in.kroid.auth.providers.phone

import android.content.Context
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import me.mo3in.kroid.auth.KroidAuth
import me.mo3in.kroid.auth.getAuthConfig
import me.mo3in.kroid.auth.models.TokenResponse
import me.mo3in.kroid.auth.providers.AuthProvider
import me.mo3in.kroid.auth.providers.emailpass.EmailPassApiService
import me.mo3in.kroid.auth.providers.phone.models.PhoneLoginRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterResponse
import me.mo3in.kroid.commons.models.DataResult
import me.mo3in.kroid.commons.services.KServiceConfig
import me.mo3in.kroid.http.HttpClient
import me.mo3in.kroid.http.extensions.runAsync

class PhoneAuthProvider : AuthProvider {
    override val providerId: String = "phone"

    fun requestOtp(phoneNumber: String): Single<DataResult<PhoneRegisterResponse>> {
        val result = SingleSubject.create<DataResult<PhoneRegisterResponse>>()

        HttpClient.build<EmailPassApiService>().requestOtp(KServiceConfig.getAuthConfig().OtpRequestEndPoint, PhoneRegisterRequest(phoneNumber)).runAsync { response ->
            result.onSuccess(response)
        }

        return result
    }

    fun verifyPhoneNumber(context: Context, phoneNumber: String, code: String): Single<DataResult<TokenResponse>> {
        val result = SingleSubject.create<DataResult<TokenResponse>>()

        HttpClient.build<EmailPassApiService>().loginOtp(KServiceConfig.getAuthConfig().OtpLoginEndPoint, PhoneLoginRequest(phoneNumber, code)).runAsync { response ->
            if (response.isSuccessful) {
                KroidAuth(context).signInWithCredential(response.data!!.access_token)
            }
            result.onSuccess(response)
        }

        return result
    }
}