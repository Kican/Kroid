package me.mo3in.kroid.commons.androidx

import android.app.Activity
import android.app.Application
import android.content.Intent
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
import java.util.concurrent.atomic.AtomicReference


@RunWith(AndroidJUnit4::class)
@Config(sdk = [16, 21, 28], application = TestApplication::class)
class ActivityResultTests {

    @Test
    fun onActivityResult() {
        val activity = Robolectric.setupActivity(TestActivity::class.java)

        val responseRef = AtomicReference<ActivityResult?>(null)

        activity.getResult().subscribe { t: ActivityResult? ->
            responseRef.set(t)
        }

        Shadows.shadowOf(activity).receiveResult(
                Intent(activity, TestResultActivity::class.java),
                Activity.RESULT_OK,
                Intent().putExtra("title", "salam")
        )

        val result = responseRef.get()!!
        Assert.assertTrue(result.isOk() && result.data!!.getStringExtra("title") == "salam")
    }
}

class TestActivity : KActivity() {

    fun getResult(): Single<ActivityResult> {
        return startActivityForResult(Intent(this, TestResultActivity::class.java))
    }
}

class TestResultActivity : Activity() {
}

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat)
    }
}