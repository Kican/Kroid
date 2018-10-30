package me.mo3in.kroid.material

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.mo3in.kroid.commons.helpers.KActivity
import me.mo3in.kroid.material.helper.KRecyclerAdapter
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [16, 21, 28], application = TestApplication::class, resourceDir = "src/test/resources")
class RecycleViewTests : Assert() {
    @get:Rule
    val rule = ActivityTestRule(TestActivity::class.java)

    @Test
    fun onActivityResult() {
//        val activity = Robolectric.buildActivity(TestActivity::class.java).create().resume().get()


        System.out.println(rule.activity.adapter.itemCount)

        Assert.assertTrue(rule.activity.adapter.itemCount == 3)
    }
}

class TestActivity : KActivity() {
    val adapter = AirplaneHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = RecyclerView(this)
        setContentView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter

        adapter.addItems(arrayOf(FootBall(1), FootBall(2), FootBall(3)))
    }
}

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat)
    }
}


data class FootBall(val id: Int)

class AirplaneHistoryAdapter : KRecyclerAdapter<FootBall>() {
    override val adapterItems: Array<AdapterItemHolder>
        get() = arrayOf(AdapterItem(R.layout.test, FootBall::class.java, ViewHolder::class.java))


    class ViewHolder(itemView: View) : ItemViewHolder<FootBall>(itemView) {
        override fun bindItem(item: FootBall, pos: Int) {
            itemView.findViewById<TextView>(R.id.text).text = "id: ${item.id}"
        }
    }
}