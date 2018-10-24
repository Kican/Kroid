package me.mo3in.kroid.commons.androidx

import androidx.test.runner.AndroidJUnit4
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import java.util.concurrent.atomic.AtomicReference

@RunWith(AndroidJUnit4::class)
@Config(sdk = [16, 21, 28], application = TestApplication::class)
class RequestPermissionTests {

    @Test
    fun onActivityResult() {
        val activity = Robolectric.setupActivity(RequestPermissionActivity::class.java)

        val responseRef = AtomicReference<Boolean?>(null)

//        activity.getSinglePermission().subscribe { t ->
//            responseRef.set(t)
//        }

//        Shadows.shadowOf(activity).receiveResult(
//                Intent(activity, TestResultActivity::class.java),
//                Activity.RESULT_OK,
//                Intent().putExtra("title", "salam")
//        )

        val result = responseRef.get()!!
        Assert.assertTrue(result)
    }
}

class RequestPermissionActivity : KActivity() {

    fun getSinglePermission(): Single<Boolean> {
        return requestPermissions(android.Manifest.permission.CALL_PHONE)
    }
}