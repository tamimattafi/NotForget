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

    override fun setIsDone(isDone: Boolean) {
        with(itemView.check) {
            setOnCheckedChangeListener(null)
            isChecked = isDone

            setOnCheckedChangeListener { _, _ ->
                onAction?.invoke(Actions.ACTION_TOGGLE)
            }
        }
    }

    override fun setCategory(category: String) {
        with(itemView.header) {
            text = category
            visibility = Visibility.VISIBLE
        }
    }

    override fun hideCategory() {
        itemView.header.visibility = Visibility.GONE
    }

}