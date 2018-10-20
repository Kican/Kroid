package me.mo3in.kroid.auth.providers.phone.models

data class PhoneLoginRequest(
        val phone: String,
        val code: String
)