package me.mo3in.kroid.commons.androidx.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import me.mo3in.kroid.commons.extensions.*

fun Fragment.dip(value: Int): Int = requireActivity().dip(value)
fun Fragment.dip(value: Float): Int = requireActivity().dip(value)
fun Fragment.sp(value: Int): Int = requireActivity().sp(value)
fun Fragment.sp(value: Float): Int = requireActivity().sp(value)
fun Fragment.px2dip(px: Int): Float = requireActivity().px2dip(px)
fun Fragment.px2sp(px: Int): Float = requireActivity().px2sp(px)
fun Fragment.dimen(resource: Int): Int = requireActivity().dimen(resource)
