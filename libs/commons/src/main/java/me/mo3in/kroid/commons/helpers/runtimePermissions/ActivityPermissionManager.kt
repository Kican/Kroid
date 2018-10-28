package me.mo3in.kroid.commons.helpers.runtimePermissions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import me.mo3in.kroid.commons.helpers.KActivity

class ActivityPermissionManager {
    val permissionsRequests = PublishSubject.create<Pair<String, Boolean>>()

    fun request(act: Activity, permission: String): Single<Boolean> {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (act.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
                grantPermission(permission)
            else
                ActivityCompat.requestPermissions(act, arrayOf(permission), KActivity.REQUEST_PERMISSION)
        } else
            grantPermission(permission)


        return permissionsRequests.filter { x -> x.first == permission }.map { x -> x.second }.firstOrError()
    }

    fun grantPermission(permission: String) {
        permissionsRequests.onNext(Pair(permission, true))
    }

    fun denyPermission(permission: String) {
        permissionsRequests.onNext(Pair(permission, false))
    }
}
