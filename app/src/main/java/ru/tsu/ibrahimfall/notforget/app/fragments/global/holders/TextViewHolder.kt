package ru.tsu.ibrahimfall.notforget.app.fragments.global.holders

import android.view.View
import kotlinx.android.synthetic.main.holder_view_text.view.*
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.RecyclerHolder

class TextViewHolder(itemView: View) : RecyclerHolder(itemView), HoldersContract.TextHolder {

    override fun setText(text: String) {
        itemView.text.text = text
    }

    override fun getText(): String = itemView.text.text.toString()


}