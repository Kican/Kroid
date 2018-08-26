package me.mo3in.kroid.commons.extensions

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.ByteArrayInputStream


fun Bitmap.toBase64(): String {

    var base64Bitmap: String? = null

    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)

    val imageBitmap = stream.toByteArray()
    base64Bitmap = Base64.encodeToString(imageBitmap, Base64.DEFAULT)

    return base64Bitmap
}

fun Bitmap.toInputStream(): InputStream {
    val baos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, baos)

    return ByteArrayInputStream(baos.toByteArray())
}

fun Bitmap.toBytes(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}