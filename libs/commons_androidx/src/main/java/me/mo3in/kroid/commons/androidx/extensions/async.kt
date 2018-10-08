package me.mo3in.kroid.commons.androidx.extensions

import androidx.fragment.app.Fragment

inline fun Fragment.runOnUiThread(crossinline f: () -> Unit) {
    requireActivity().runOnUiThread { f() }
}