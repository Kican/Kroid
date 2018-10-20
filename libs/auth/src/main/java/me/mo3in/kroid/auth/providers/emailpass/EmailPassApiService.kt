package me.mo3in.kroid.auth.providers.emailpass

import me.mo3in.kroid.auth.models.TokenResponse
import me.mo3in.kroid.auth.providers.phone.models.PhoneLoginRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface EmailPassApiService {
    @POST
    fun requestOtp(@Url url: String, @Body request: PhoneRegisterRequest): Call<PhoneRegisterResponse>

    @POST
    fun loginOtp(@Url url: String, @Body request: PhoneLoginRequest): Call<TokenResponse>
}