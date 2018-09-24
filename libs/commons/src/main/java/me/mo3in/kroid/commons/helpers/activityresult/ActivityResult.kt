package me.mo3in.kroid.commons.helpers.activityresult

import android.content.Intent
import android.app.Activity


data class ActivityResult(val resultCode: Int, val data: Intent?) {
    fun isOk(): Boolean {
        return resultCode == Activity.RESULT_OK
    }

    fun isCanceled(): Boolean {
        return resultCode == Activity.RESULT_CANCELED
    }

    fun isFirstUser(): Boolean {
        return resultCode == Activity.RESULT_FIRST_USER
    }
}