package me.mo3in.kroid.auth.providers.emailpass

import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterRequest
import me.mo3in.kroid.auth.providers.phone.models.PhoneRegisterResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailPassApiService {
    @POST
    fun requestOtp(@Body request: PhoneRegisterRequest): Call<PhoneRegisterResponse>

}