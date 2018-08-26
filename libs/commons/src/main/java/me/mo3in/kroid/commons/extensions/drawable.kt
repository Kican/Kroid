package me.mo3in.kroid.commons.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable


fun Drawable.toBitmap(): Bitmap? {
    if (this is BitmapDrawable) {
        return bitmap
    }

    if (intrinsicWidth <= 0 && intrinsicHeight <= 0) {
        return null
    }

    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}