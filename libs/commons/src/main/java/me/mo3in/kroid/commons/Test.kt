package me.mo3in.kroid.commons

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import me.mo3in.kroid.commons.helpers.KActivityRules
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResultManager

abstract class KActivity : AppCompatActivity(), KActivityRules {
    private val REQUEST_PERMISSION = 1111
    private val NEEDED_PERMISSIONS = 2222

    override val activityResultManager: ActivityResultManager = ActivityResultManager()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultManager.activityResult.onNext(Pair(requestCode, ActivityResult(resultCode, data)))
    }

//    fun requestPermissions(permission: String): Single<Boolean> {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
//                permissionCallback.permissionGranted()
//                return
//            }
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
//                ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_PERMISSION)
//            else
//                ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_PERMISSION)
//
//
//        } else
//            permissionCallback.permissionGranted()
//    }

    private fun requestNeededPermission(permissionsNeed: MutableList<String>) {
        ActivityCompat.requestPermissions(this@KActivity, permissionsNeed.toTypedArray(), NEEDED_PERMISSIONS)
    }


//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if (requestCode == REQUEST_PERMISSION) {
//
//            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                pCallback.permissionGranted()
//            else
//                pCallback.permissionDenied()
//
//        } else if (requestCode == NEEDED_PERMISSIONS) {
//
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                pCallback.permissionGranted()
//            else
//                pCallback.permissionDenied()
//
//        } else
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//    }
}

abstract class KMVVMActivity<TVM : ViewModel>(private val klass: Class<TVM>) : KActivity() {
    lateinit var viewModel: TVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(klass)
    }
}