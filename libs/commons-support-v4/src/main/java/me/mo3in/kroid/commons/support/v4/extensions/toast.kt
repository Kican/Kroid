package me.mo3in.kroid.commons.support.v4.extensions

import android.support.v4.app.Fragment
import me.mo3in.kroid.commons.extensions.longToast
import me.mo3in.kroid.commons.extensions.toast

inline fun Fragment.toast(textResource: Int) = requireActivity().toast(textResource)

inline fun Fragment.toast(text: String) = requireActivity().toast(text)

inline fun Fragment.longToast(textResource: Int) = requireActivity().longToast(textResource)

inline fun Fragment.longToast(text: String) = requireActivity().longToast(text)