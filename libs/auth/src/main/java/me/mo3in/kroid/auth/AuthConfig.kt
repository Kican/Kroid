package me.mo3in.kroid.auth

import me.mo3in.kroid.commons.services.KServiceConfig

fun KServiceConfig.setAuthConfig(endPoint: AuthConfig): KServiceConfig {
    this.config["auth.config"] = endPoint
    return this;
}

fun KServiceConfig.getAuthConfig(): AuthConfig = config["auth.config"] as AuthConfig

class AuthConfig(
        var endPoint: String,
        var emailPassLoginPath: String? = null,
        var emailPathRegisterPath: String? = null,
        val userInfoPath: String? = null,
        val editUserInfoPath: String? = null,
        var totpRequestPath: String? = null,
        var totpLoginPath: String? = null) {
    companion object {
        fun Kasp(endPoint: String): AuthConfig = AuthConfig(
                endPoint,
                "api/Account/Login",
                "api/Account/Register",
                "api/Account/Info",
                "api/Account/Edit",
                "api/Account/PhoneRequest",
                "api/account/LoginOtp"
        )
    }

    val registerEndPoint: String = this.endPoint + this.emailPathRegisterPath
    val LoginEndPoint: String = this.endPoint + this.emailPassLoginPath
    val OtpLoginEndPoint: String = this.endPoint + this.totpLoginPath
    val OtpRequestEndPoint: String = this.endPoint + this.totpRequestPath
    val UserInfoEndPoint: String = this.endPoint + this.userInfoPath
}
