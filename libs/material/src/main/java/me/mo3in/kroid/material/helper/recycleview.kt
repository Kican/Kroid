package me.mo3in.kroid.material.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import me.mo3in.kroid.material.R

abstract class KRecyclerAdapter<TModel : Any> : RecyclerView.Adapter<KRecyclerAdapter.ItemViewHolder<TModel>>() {
    protected var items: ArrayList<TModel> = ArrayList()

    var onClick: ((view: View, position: Int, item: TModel) -> Unit)? = null
    var onLongClick: ((view: View, position: Int, item: TModel) -> Boolean)? = null

    protected open fun handleClick(viewHolder: ItemViewHolder<TModel>, clickPosition: (ItemViewHolder<TModel>) -> Int) {
        val itemView = viewHolder.itemView

        itemView.setOnClickListener {
            val position = clickPosition(viewHolder)
            onClick?.invoke(itemView, position, getItem(position))
        }

        onLongClick?.let {
            val position = clickPosition(viewHolder)
            itemView.setOnLongClickListener { it(itemView, position, getItem(position)) }
        }
    }

    abstract val adapterItems: Array<AdapterItemHolder>


    override fun getItemViewType(position: Int): Int = adapterItems.indexOfFirst { item -> item.isModel(items[position]) }

    override fun onBindViewHolder(holder: ItemViewHolder<TModel>, position: Int) {
        val item = items[position]
        holder.bindItem(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<TModel> {
        val view = LayoutInflater.from(parent.context).inflate(adapterItems[viewType].layout, parent, false)
        val viewHolder = adapterItems[viewType].viewHolder.getConstructor(View::class.java).newInstance(view)

        handleClick(viewHolder as ItemViewHolder<TModel>, { it.layoutPosition })

        return viewHolder
    }


    fun addItem(item: TModel) {
        items.add(item)
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

    abstract class ItemViewHolder<TModel : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        abstract fun bindItem(item: TModel, pos: Int)
    }

    override fun getItemCount(): Int = items.count()

    inner class AdapterItem<T : TModel, TViewModel : ItemViewHolder<T>>(@LayoutRes layout: Int, modelClass: Class<T>, viewHolder: Class<TViewModel>) : AdapterItemHolder(layout, modelClass, viewHolder)

    open class AdapterItemHolder(val layout: Int, val modelClass: Class<*>, val viewHolder: Class<*>) {
        fun <T> isModel(model: T): Boolean = modelClass.isInstance(model)
    }

}