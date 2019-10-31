package ru.tsu.ibrahimfall.notforget.app.fragments.global.holders

import android.view.View
import kotlinx.android.synthetic.main.holder_view_task.view.*
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Actions
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Visibility
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.Holder
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.RecyclerHolder

class TaskViewHolder(itemView: View) : RecyclerHolder(itemView), Holder {

    override fun setTitle(title: String) {
        itemView.title.text = title
    }

    override fun setDescription(description: String) {
        itemView.description.text = description
    }

    override fun setImportanceColor(color: Int) {
        itemView.root.setBackgroundColor(color)
    }

    override fun setChecked(checked: Boolean) {
        with(itemView.check) {
            setOnCheckedChangeListener(null)
            isChecked = checked

            setOnCheckedChangeListener { _, _ ->
                onAction?.invoke(Actions.ACTION_TOGGLE)
            }
        }
    }

    override fun showHeader(header: String) {
        with(itemView.header) {
            text = header
            visibility = Visibility.VISIBLE
        }
    }

    override fun hideHeader() {
        itemView.header.visibility = Visibility.GONE
    }

}