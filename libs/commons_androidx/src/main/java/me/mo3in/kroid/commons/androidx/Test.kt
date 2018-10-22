package me.mo3in.kroid.commons.androidx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import me.mo3in.kroid.commons.helpers.KActivityRules
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResult
import me.mo3in.kroid.commons.helpers.activityresult.ActivityResultManager


abstract class KActivity : AppCompatActivity(), KActivityRules {
    override val activityResultManager: ActivityResultManager = ActivityResultManager()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        activityResultManager.activityResult.onNext(Pair(requestCode, ActivityResult(resultCode, data)))
    }
}

abstract class KMVVMActivity<TVM : ViewModel>(private val klass: Class<TVM>) : KActivity() {
    lateinit var viewModel: TVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(klass)
    }
}