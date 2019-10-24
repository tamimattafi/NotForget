package ru.tsu.ibrahimfall.notforget.mvp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.IllegalArgumentException

abstract class RecyclerAdapter<H : Holder>(private val view: ListenerView<H>) :
    RecyclerView.Adapter<ViewHolder>(), Adapter {

    override var isLoading: Boolean = false
    private var dataCount: Int = 0

    abstract fun getEmptyHolder(): ViewHolder
    abstract fun getItemHolder(): ViewHolder
    abstract fun getLoadingHolder(): ViewHolder

    override fun setDataCount(dataCount: Int) {
        this.dataCount = dataCount
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE_LOADING -> getLoadingHolder()
            TYPE_ITEM -> getItemHolder()
            TYPE_EMPTY -> getEmptyHolder()
            else -> throw IllegalArgumentException("${AdapterConstants.VIEW_TYPE_ERROR}: $viewType")
        }

    override fun getItemCount(): Int = if (isLoading || dataCount == 0) 1 else dataCount

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as? H)?.apply {
            listener = view
            listPosition = position
            view.bindHolder(this)
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        getLoadingCondition(position) -> TYPE_LOADING
        getEmptyCondition(position) -> TYPE_EMPTY
        getItemCondition(position) -> TYPE_ITEM
        else -> super.getItemViewType(position)
    }

    open fun getLoadingCondition(position: Int): Boolean =
        position == 0 && dataCount == 0 && isLoading

    open fun getEmptyCondition(position: Int): Boolean = position == 0 && dataCount == 0

    open fun getItemCondition(position: Int): Boolean = position > 0

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_LOADING = 1
        const val TYPE_EMPTY = 2
    }
}