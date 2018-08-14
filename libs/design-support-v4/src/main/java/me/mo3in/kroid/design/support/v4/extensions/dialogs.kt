package me.mo3in.kroid.design.support.v4.extensions

import android.content.DialogInterface
import android.support.v4.app.Fragment
import me.mo3in.kroid.design.dialogs.AlertBuilder
import me.mo3in.kroid.design.extensions.alert
import me.mo3in.kroid.design.extensions.selector


inline fun Fragment.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        noinline onClick: (DialogInterface, Int) -> Unit
): Unit = requireActivity().selector(title, items, onClick)

inline fun Fragment.alert(
        message: String,
        title: String? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = requireActivity().alert(message, title, init)

inline fun Fragment.alert(
        message: Int,
        title: Int? = null,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = requireActivity().alert(message, title, init)

inline fun Fragment.alert(noinline init: AlertBuilder<DialogInterface>.() -> Unit) = requireActivity().alert(init)
