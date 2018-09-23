package me.mo3in.kroid.commons.androidx.extensions

import androidx.fragment.app.Fragment
import me.mo3in.kroid.commons.extensions.longToast
import me.mo3in.kroid.commons.extensions.toast

fun Fragment.toast(textResource: Int) = requireActivity().toast(textResource)

fun Fragment.toast(text: String) = requireActivity().toast(text)

fun Fragment.longToast(textResource: Int) = requireActivity().longToast(textResource)

fun Fragment.longToast(text: String) = requireActivity().longToast(text)