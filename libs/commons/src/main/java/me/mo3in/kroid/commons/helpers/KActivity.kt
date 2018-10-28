package me.mo3in.kroid.commons.helpers

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.reactivex.Single
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResultManager
import me.mo3in.kroid.commons.helpers.runtimePermissions.ActivityPermissionManager

abstract class KActivity : AppCompatActivity() {
    companion object {
        val REQUEST_PERMISSION = 1111
        val NEEDED_PERMISSIONS = 2222
    }

    val activityResultManager: ActivityResultManager = ActivityResultManager()
    val activityPermission: ActivityPermissionManager = ActivityPermissionManager()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultManager.activityResult.onNext(Pair(requestCode, ActivityResult(resultCode, data)))
    }

    fun requestPermissions(permission: String): Single<Boolean> {
        return activityPermission.request(this, permission)
    }

    private fun requestNeededPermission(permissionsNeed: MutableList<String>) {
        ActivityCompat.requestPermissions(this@KActivity, permissionsNeed.toTypedArray(), NEEDED_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION) {

            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                activityPermission.grantPermission(permissions[0])
            else
                activityPermission.denyPermission(permissions[0])

        } else if (requestCode == NEEDED_PERMISSIONS) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                activityPermission.grantPermission(permissions[0])
            else
                activityPermission.denyPermission(permissions[0])

        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}