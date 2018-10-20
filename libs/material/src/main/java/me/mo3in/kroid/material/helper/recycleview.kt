package me.mo3in.kroid.material.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import me.mo3in.kroid.material.R
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

open class KRecyclerAdapter<TModel : Any> : RecyclerView.Adapter<KRecyclerAdapter.ItemViewHolder<TModel>> {
    protected var items: ArrayList<TModel> = ArrayList()


    var onClick: ((view: View, position: Int, item: TModel) -> Unit)? = null
    var onLongClick: ((view: View, position: Int, item: TModel) -> Boolean)? = null

    val viewHolders: ArrayList<AdapterItemHolder> = arrayListOf()

    constructor()

    constructor(viewHolder: AdapterItemHolder) : super() {
        viewHolders.add(viewHolder)
    }

    constructor(_viewHolders: ArrayList<AdapterItemHolder>) : super() {
        viewHolders.addAll(_viewHolders)
    }

    fun addItem(item: TModel) {
        items.add(item)
        notifyItemRangeInserted(itemCount - 1, 1)
    }

    fun addItem(index: Int, item: TModel) {
        items.add(index, item)
        notifyItemRangeInserted(itemCount - 1, 1)
    }

    fun addItems(_items: Array<TModel>) {
        items.addAll(_items)
        notifyItemRangeInserted(itemCount - _items.count(), _items.count())
    }

    fun setItems(_items: Array<TModel>) {
        items.clear()
        items.addAll(_items)
        notifyDataSetChanged()
    }

    fun getItems(): MutableList<TModel> = items

    fun getItem(position: Int): TModel = items[position]

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun removeItem(pos: Int) {
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    private fun handleClick(viewHolder: ItemViewHolder<TModel>, clickPosition: (ItemViewHolder<TModel>) -> Int) {
        val itemView = viewHolder.itemView

        itemView.setOnClickListener {
            val position = clickPosition(viewHolder)
            onClick?.invoke(itemView, position, getItem(position))
        }

        onLongClick?.let { it ->
            val position = clickPosition(viewHolder)
            itemView.setOnLongClickListener { it(itemView, position, getItem(position)) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<TModel> {
        val view = viewHolders[viewType].getView(LayoutInflater.from(parent.context), parent)
        val viewHolder = viewHolders[viewType].viewHolder.getConstructor(View::class.java).newInstance(view)

        handleClick(viewHolder as ItemViewHolder<TModel>, { it.layoutPosition })

        return viewHolder
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ItemViewHolder<TModel>, position: Int) {
        val item = items[position]
        holder.bindItem(item, position)
    }

    inner class AdapterItem<T : TModel, TViewModel : ItemViewHolder<T>>(@LayoutRes layout: Int, modelClass: Class<T>, viewHolder: Class<TViewModel>) : AdapterItemHolder(modelClass, viewHolder)


    public open class AdapterItemHolder(val modelClass: Class<*>, val viewHolder: Class<*>) {
        var layoutRes: Int = 0

        open fun getView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(layoutRes, parent, false)
        }

        fun <T> isModel(model: T): Boolean = modelClass.isInstance(model)
    }


    abstract class ItemViewHolder<TModel : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        abstract fun bindItem(item: TModel, pos: Int)
    }
}

data class FootBall(val id: Int)

//@LayoutId(R.layout.test)
class FootballViewHolder(itemView: View) : KRecyclerAdapter.ItemViewHolder<FootBall>(itemView) {
    override fun bindItem(item: FootBall, pos: Int) {

    }
}

//
//class Test {
//    init {
//        KRecyclerAdapter<FootBall>(KRecyclerAdapter.AdapterItem(FootBall::class.java, FootballViewHolder::class.java))
//    }
//}

class FootballAdapterItem : KRecyclerAdapter.AdapterItemHolder(FootBall::class.java, FootballViewHolder::class.java) {

}

class AirplaneHistoryAdapter : KRecyclerAdapter<FootBall>() {

    init {
        viewHolders.add(AdapterItem(R.layout.test, FootBall::class.java, ViewHolder::class.java))
    }

    class ViewHolder(itemView: View) : ItemViewHolder<FootBall>(itemView) {
        override fun bindItem(item: FootBall, pos: Int) {

        }
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LayoutId(val value: Int)