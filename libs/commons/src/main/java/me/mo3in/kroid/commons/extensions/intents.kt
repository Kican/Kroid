package me.mo3in.kroid.commons.extensions

import android.app.Activity
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import me.mo3in.kroid.commons.Utils
import me.mo3in.kroid.commons.helpers.KActivity


// start activity

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
        Utils.internalStartActivity(this, T::class.java, params)

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    Utils.internalStartActivity(requireActivity(), T::class.java, params)
}

// start activity for result

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) =
        Utils.internalStartActivityForResult(this, T::class.java, requestCode, params)

inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult(Utils.createIntent(requireActivity(), T::class.java, params), requestCode)
}

// start service

inline fun <reified T : Service> Context.startService(vararg params: Pair<String, Any?>) =
        Utils.internalStartService(this, T::class.java, params)

inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any?>) {
    Utils.internalStartService(requireActivity(), T::class.java, params)
}

// stop service

inline fun <reified T : Service> Context.stopService(vararg params: Pair<String, Any?>) =
        Utils.internalStopService(this, T::class.java, params)

inline fun <reified T : Service> Fragment.stopService(vararg params: Pair<String, Any?>) {
    Utils.internalStopService(requireActivity(), T::class.java, params)
}

// make intent
inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent =
        Utils.createIntent(this, T::class.java, params)

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
        Utils.createIntent(requireActivity(), T::class.java, params)

//  for browse url

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        if (newTask)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

fun Fragment.browse(url: String, newTask: Boolean = false): Boolean = requireActivity().browse(url, newTask)

//  for share text

fun Context.share(text: String, subject: String = ""): Boolean {
    return try {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

fun Fragment.share(text: String, subject: String = ""): Boolean = requireActivity().share(text, subject)

// for send email

fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)

    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))

    if (subject.isNotEmpty())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)

    if (text.isNotEmpty())
        intent.putExtra(Intent.EXTRA_TEXT, text)

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false
}

fun Fragment.email(email: String, subject: String = "", text: String = ""): Boolean =
        requireActivity().email(email, subject, text)

// for make call

fun Context.makeCall(number: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun Fragment.makeCall(number: String): Boolean = requireActivity().makeCall(number)

// for send sms

fun Context.sendSMS(number: String, text: String = ""): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
        intent.putExtra("sms_body", text)
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun Fragment.sendSMS(number: String, text: String = ""): Boolean = requireActivity().sendSMS(number, text)