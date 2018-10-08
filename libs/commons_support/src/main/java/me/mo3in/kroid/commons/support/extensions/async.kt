package me.mo3in.kroid.commons.support.extensions

import android.support.v4.app.Fragment


inline fun Fragment.runOnUiThread(crossinline f: () -> Unit) {
    requireActivity().runOnUiThread { f() }
}