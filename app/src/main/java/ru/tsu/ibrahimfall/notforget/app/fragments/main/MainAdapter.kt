package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.TaskViewHolder
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.Adapter
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.View
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.empty.EmptyHolder
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.empty.UnbindableHolder

class MainAdapter(view: View) : Adapter(view) {

    override fun getEmptyHolder(parent: ViewGroup): RecyclerView.ViewHolder = EmptyHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.holder_view_empty,
            parent,
            false
        ), MainValues.getPlaceholder(parent.context.resources)
    )


    override fun getItemHolder(parent: ViewGroup): RecyclerView.ViewHolder = TaskViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.holder_view_task,
            parent,
            false
        )
    )


    override fun getLoadingHolder(parent: ViewGroup): RecyclerView.ViewHolder = UnbindableHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.holder_view_loading,
            parent,
            false
        )
    )

}