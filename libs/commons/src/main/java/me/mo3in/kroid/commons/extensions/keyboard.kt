package me.mo3in.kroid.commons.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun Activity.hideSoftInput() {
    var view = currentFocus
    if (view == null) view = View(this)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.showSoftInput() {
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    context.inputMethodManager.showSoftInput(this, 0)
}

fun Context.toggleSoftInput() {
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}