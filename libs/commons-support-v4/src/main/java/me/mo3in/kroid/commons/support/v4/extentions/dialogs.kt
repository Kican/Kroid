package me.mo3in.kroid.commons.support.v4.extentions

import android.content.DialogInterface
import android.support.v4.app.Fragment


inline fun Fragment.selector(title: CharSequence? = null, items: List<CharSequence>, noinline onClick: (DialogInterface, Int) -> Unit): Unit =
        requireActivity().selector(title, items, onClick)

inline fun Fragment.alert(message: String, title: String? = null, noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null) =
        requireActivity().alert(message, title, init)

inline fun Fragment.alert(message: Int, title: Int? = null, noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null) =
        requireActivity().alert(message, title, init)

inline fun Fragment.alert(noinline init: AlertBuilder<DialogInterface>.() -> Unit) =
        requireActivity().alert(init)
