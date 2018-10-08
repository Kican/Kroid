package me.mo3in.kroid.commons.support.extensions

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.support.v4.app.Fragment
import me.mo3in.kroid.commons.Utils
import me.mo3in.kroid.commons.extensions.*

// start activity
inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    Utils.internalStartActivity(requireActivity(), T::class.java, params)
}

// start activity for result
inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult(Utils.createIntent(requireActivity(), T::class.java, params), requestCode)
}

// start service
inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any?>) {
    Utils.internalStartService(requireActivity(), T::class.java, params)
}

// stop service
inline fun <reified T : Service> Fragment.stopService(vararg params: Pair<String, Any?>) {
    Utils.internalStopService(requireActivity(), T::class.java, params)
}

// make intent
inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
        Utils.createIntent(requireActivity(), T::class.java, params)

// browse
fun Fragment.browse(url: String, newTask: Boolean = false): Boolean = requireActivity().browse(url, newTask)


// share
fun Fragment.share(text: String, subject: String = ""): Boolean = requireActivity().share(text, subject)

// email
fun Fragment.email(email: String, subject: String = "", text: String = ""): Boolean =
        requireActivity().email(email, subject, text)

// call
fun Fragment.makeCall(number: String): Boolean = requireActivity().makeCall(number)

// sms
fun Fragment.sendSMS(number: String, text: String = ""): Boolean = requireActivity().sendSMS(number, text)