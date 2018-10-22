package me.mo3in.kroid.commons.helpers

import android.app.Activity
import android.content.Intent
import io.reactivex.Single
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResultManager

fun KActivityRules.startActivityForResult(intent: Intent): Single<ActivityResult> {
    return activityResultManager.start(this as Activity, intent)
}

interface KActivityRules {
    val activityResultManager: ActivityResultManager

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}