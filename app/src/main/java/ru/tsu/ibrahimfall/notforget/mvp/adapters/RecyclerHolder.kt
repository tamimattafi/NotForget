package ru.tsu.ibrahimfall.notforget.mvp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView), MvpBaseContract.Holder {

    override var listPosition: Int = -1

    override var listener: MvpBaseContract.AdapterListener? = null
        set(value) {
            field = value?.also { listener ->
                itemView.setOnClickListener {
                    listener.onHolderClick(listPosition)
                }
            }
        }

}