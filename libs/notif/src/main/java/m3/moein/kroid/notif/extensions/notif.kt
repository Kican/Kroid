package m3.moein.kroid.notif.extensions

import android.content.Context
import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import io.karn.notify.Notify
import io.karn.notify.NotifyCreator

fun Context.notify(): NotifyCreator = Notify.with(this)
fun Fragment.notify(): NotifyCreator = Notify.with(requireActivity())

fun Context.notif(title: String, text: String, largeIcon: Bitmap? = null) {
    notify().content {
        this.title = title
        this.text = text
        this.largeIcon = largeIcon
    }.show()
}

fun Fragment.notif(title: String, text: String, largeIcon: Bitmap? = null) = requireActivity().notif(title, text, largeIcon)

fun Context.textListNotif(lines: List<String>, title: String, text: String, largeIcon: Bitmap? = null) {
    notify().asTextList {
        this.lines = lines
        this.largeIcon = largeIcon
        this.title = title
        this.text = text
    }.show()
}

fun Fragment.textListNotif(lines: List<String>, title: String, text: String, largeIcon: Bitmap? = null) {
    requireActivity().textListNotif(lines, title, text, largeIcon)
}