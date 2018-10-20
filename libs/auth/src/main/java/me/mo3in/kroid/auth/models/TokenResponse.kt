package me.mo3in.kroid.auth.models

data class TokenResponse(
        val access_token: String,
        val token_type: String,
        val refresh_token: String,
        val expires_in: Long
)