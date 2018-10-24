package me.mo3in.kroid.commons.androidx

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import androidx.test.runner.AndroidJUnit4
import io.reactivex.Single
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
import me.mo3in.kroid.commons.helpers.startActivityForResult
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [16, 21, 28], application = TestApplication::class)
class ActivityResultTests {

    @Test
    fun onActivityResult() {
        val activity = Robolectric.setupActivity(TestActivity::class.java)


        activity.captureImage().subscribe { t: ActivityResult? ->
            Assert.assertTrue(t!!.isOk() && t.data!!.getStringExtra("title") == "salam")
        }

        Shadows.shadowOf(activity).receiveResult(
                Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                Activity.RESULT_OK,
                Intent().putExtra("title", "salam")
        )

        activity.captureVideo().subscribe { t: ActivityResult? ->
            Assert.assertTrue(t!!.isOk() && t.data!!.getStringExtra("title") == "salam2")
        }

        Shadows.shadowOf(activity).receiveResult(
                Intent(MediaStore.ACTION_VIDEO_CAPTURE),
                Activity.RESULT_OK,
                Intent().putExtra("title", "salam2")
        )

    }
}

class TestActivity : KActivity() {
    fun captureImage(): Single<ActivityResult> {
        return startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    fun captureVideo(): Single<ActivityResult> {
        return startActivityForResult(Intent(MediaStore.ACTION_VIDEO_CAPTURE))
    }
}

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat)
    }
}