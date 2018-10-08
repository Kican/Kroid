package me.mo3in.kroid.commons.support.extensions

import android.support.v4.app.Fragment
import me.mo3in.kroid.commons.extensions.longToast
import me.mo3in.kroid.commons.extensions.toast

// for fragments

fun Fragment.toast(textResource: Int) = requireActivity().toast(textResource)
fun Fragment.toast(text: String) = requireActivity().toast(text)
fun Fragment.longToast(textResource: Int) = requireActivity().longToast(textResource)
fun Fragment.longToast(text: String) = requireActivity().longToast(text)