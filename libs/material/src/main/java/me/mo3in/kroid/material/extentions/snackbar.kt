package me.mo3in.kroid.material.extentions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.snackbar(@StringRes message: Int) = Snackbar
        .make(this, message, Snackbar.LENGTH_SHORT)
        .apply { show() }


fun View.longSnackbar(@StringRes message: Int) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .apply { show() }

fun View.indefiniteSnackbar(@StringRes message: Int) = Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .apply { show() }

fun View.snackbar(message: CharSequence) = Snackbar
        .make(this, message, Snackbar.LENGTH_SHORT)
        .apply { show() }

fun View.longSnackbar(message: CharSequence) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .apply { show() }

fun View.indefiniteSnackbar(message: CharSequence) = Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .apply { show() }

fun View.snackbar(message: Int, @StringRes actionText: Int, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_SHORT)
        .setAction(actionText, action)
        .apply { show() }

fun View.longSnackbar(@StringRes message: Int, @StringRes actionText: Int, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionText, action)
        .apply { show() }

fun View.indefiniteSnackbar(@StringRes message: Int, @StringRes actionText: Int, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText, action)
        .apply { show() }

fun View.snackbar(message: CharSequence, actionText: CharSequence, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_SHORT)
        .setAction(actionText, action)
        .apply { show() }

fun View.longSnackbar(message: CharSequence, actionText: CharSequence, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionText, action)
        .apply { show() }

fun View.indefiniteSnackbar(message: CharSequence, actionText: CharSequence, action: (View) -> Unit) = Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText, action)
        .apply { show() }