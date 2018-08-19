package me.mo3in.kroid.commons.extensions

import android.content.Context


fun Context.isNetworkConnected(): Boolean {
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}