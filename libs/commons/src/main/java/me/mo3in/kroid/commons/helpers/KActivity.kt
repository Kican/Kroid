package me.mo3in.kroid.commons.helpers

//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import io.reactivex.Single
//import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
//import me.mo3in.kroid.commons.helpers.activityresult.ActivityResultManager
//
//abstract class KActivity : AppCompatActivity() {
//    private val activityResultManager = ActivityResultManager()
//
//    fun startActivityForResult(intent: Intent): Single<ActivityResult> {
//        return activityResultManager.start(this, intent)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        activityResultManager.activityResult.onNext(Pair(requestCode, ActivityResult(resultCode, data)))
//    }
//}