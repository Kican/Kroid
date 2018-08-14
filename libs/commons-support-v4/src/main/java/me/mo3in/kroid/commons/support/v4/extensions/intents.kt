package me.mo3in.kroid.commons.support.v4.extensions

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.support.v4.app.Fragment
import me.mo3in.kroid.commons.Utils
import me.mo3in.kroid.commons.extensions.*


fun Fragment.browse(url: String, newTask: Boolean = false): Boolean = requireActivity().browse(url, newTask)

fun Fragment.share(text: String, subject: String = ""): Boolean = requireActivity().share(text, subject)

fun Fragment.email(email: String, subject: String = "", text: String = ""): Boolean =
        requireActivity().email(email, subject, text)

fun Fragment.makeCall(number: String): Boolean = requireActivity().makeCall(number)

fun Fragment.sendSMS(number: String, text: String = ""): Boolean = requireActivity().sendSMS(number, text)

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    Utils.internalStartActivity(requireActivity(), T::class.java, params)
}

inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult(Utils.createIntent(requireActivity(), T::class.java, params), requestCode)
}

inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any?>) {
    Utils.internalStartService(requireActivity(), T::class.java, params)
}

inline fun <reified T : Service> Fragment.stopService(vararg params: Pair<String, Any?>) {
    Utils.internalStopService(requireActivity(), T::class.java, params)
}

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
        Utils.createIntent(requireActivity(), T::class.java, params)