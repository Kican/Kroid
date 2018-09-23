package me.mo3in.kroid.commons.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context.toast(resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
fun Context.longToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
fun Context.longToast(resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_LONG).show()

// for fragments

fun Fragment.toast(textResource: Int) = requireActivity().toast(textResource)
fun Fragment.toast(text: String) = requireActivity().toast(text)
fun Fragment.longToast(textResource: Int) = requireActivity().longToast(textResource)
fun Fragment.longToast(text: String) = requireActivity().longToast(text)