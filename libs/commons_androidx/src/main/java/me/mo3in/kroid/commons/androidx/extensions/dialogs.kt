package me.mo3in.kroid.commons.androidx.extensions

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import me.mo3in.kroid.commons.extensions.alert
import me.mo3in.kroid.commons.extensions.selector
import me.mo3in.kroid.commons.widgets.dialogs.AlertBuilder

// for fragments

fun Fragment.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        onClick: (DialogInterface, Int) -> Unit
): Unit = requireActivity().selector(title, items, onClick)

fun Fragment.alert(
        message: String,
        title: String? = null,
        init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = requireActivity().alert(message, title, init)

fun Fragment.alert(
        message: Int,
        title: Int? = null,
        init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
) = requireActivity().alert(message, title, init)

fun Fragment.alert(init: AlertBuilder<DialogInterface>.() -> Unit) = requireActivity().alert(init)