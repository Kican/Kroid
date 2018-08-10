package me.mo3in.kroid.design.support.v4.extentions

import android.support.v4.app.Fragment

inline fun Fragment.runOnUiThread(crossinline f: () -> Unit) {
    requireActivity().runOnUiThread { f() }
}