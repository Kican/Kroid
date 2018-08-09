package me.mo3in.kroid.http.models

enum class ResponseErrorType {
    Nothing,
    BadRequestError,
    UnAuthorizeError,
    ServerError,
    NetworkError,
    TimeOutError,
    UnknownError
}