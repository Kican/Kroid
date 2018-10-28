package me.mo3in.kroid.commons.helpers.activityresult

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import me.mo3in.kroid.commons.helpers.KActivity
import java.util.concurrent.atomic.AtomicInteger

class ActivityResultManager {
    val activityResult = PublishSubject.create<Pair<Int, ActivityResult>>()

    private val seed = AtomicInteger(500)

    fun start(act: Activity, intent: Intent): Single<ActivityResult> {
        val requestId = seed.getAndIncrement()

        act.startActivityForResult(intent, requestId)

        return activityResult.filter { x -> x.first == requestId }.map { x -> x.second }.firstOrError()
    }

//    fun start(act: KFragment, intent: Intent): Single<ActivityResult> {
//        val requestId = seed.getAndIncrement()
//
//        act.startActivityForResult(intent, requestId)
//
//        return activityResult.filter { x -> x.first == requestId }.map { x -> x.second }.firstOrError()
//    }
}

