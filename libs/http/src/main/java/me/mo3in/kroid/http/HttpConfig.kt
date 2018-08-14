package me.mo3in.kroid.http

import me.mo3in.kroid.commons.services.KServiceConfig

private const val HTTP_END_POINT = "http_end_point"

fun KServiceConfig.setHttpEndPoint(url: String): KServiceConfig {
    config[HTTP_END_POINT] = url
    return this
}

val KServiceConfig.httpEndPoint: String
    get() = config[HTTP_END_POINT] as String