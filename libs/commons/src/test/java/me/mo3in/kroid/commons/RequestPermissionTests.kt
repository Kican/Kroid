package me.mo3in.kroid.commons

import android.content.pm.PackageManager
import io.reactivex.Single
import me.mo3in.kroid.commons.helpers.KActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [16, 21, 28], application = TestApplication::class)
class RequestPermissionTests {

    @Test
    fun onActivityResult() {
        val activity = Robolectric.setupActivity(RequestPermissionActivity::class.java)
        val application = Shadows.shadowOf(activity.application)

        var count = 0

        activity.getSinglePermission().subscribe { t ->
            count++
        }

        application.grantPermissions(android.Manifest.permission.CALL_PHONE)
        Shadows.shadowOf(activity).grantPermissions(android.Manifest.permission.CALL_PHONE)
//        activity.onRequestPermissionsResult(KActivity.REQUEST_PERMISSION,
//                arrayOf(android.Manifest.permission.CALL_PHONE),
//                intArrayOf(PackageManager.PERMISSION_GRANTED)
//        )

        Assert.assertTrue(count == 1)
    }

    @Test
    fun onActivityResultAfterPermission() {
        val activity = Robolectric.setupActivity(RequestPermissionActivity::class.java)
        val application = Shadows.shadowOf(activity.application)

        val responseRef = AtomicReference<Boolean>(false)
        val latch = CountDownLatch(1)

        application.grantPermissions(android.Manifest.permission.CALL_PHONE)
        Shadows.shadowOf(activity).grantPermissions(android.Manifest.permission.CALL_PHONE)
        activity.onRequestPermissionsResult(KActivity.REQUEST_PERMISSION,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                intArrayOf(PackageManager.PERMISSION_GRANTED)
        )

        activity.getSinglePermission().subscribe { t ->
            responseRef.set(true)
            latch.countDown()
        }

        latch.await()
        Assert.assertTrue(responseRef.get())
    }
}

class RequestPermissionActivity : KActivity() {
    fun getSinglePermission(): Single<Boolean> {
        return requestPermissions(android.Manifest.permission.CALL_PHONE)
    }
}