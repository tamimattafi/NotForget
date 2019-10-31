package ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.empty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_view_empty.view.*
import ru.tsu.ibrahimfall.notforget.utils.AppUtils

class EmptyHolder(itemView: View, data: Placeholder) : RecyclerView.ViewHolder(itemView) {

    init {
        with(itemView) {
            data.apply {
                placeholder.setImageDrawable(AppUtils.getDrawable(context, backgroundRes))
                this@with.description.text = description
            }
        }
    }

}