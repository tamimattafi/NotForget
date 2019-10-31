package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.graphics.Color
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.*
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainValues.DEFAULT_CATEGORY_NAME
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainValues.DEFAULT_DESCRIPTION
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainValues.DEFAULT_PRIORITY_COLOR
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainValues.DEFAULT_TITLE
import ru.tsu.ibrahimfall.notforget.model.items.task.Task
import ru.tsu.ibrahimfall.notforget.mvp.presenters.RecyclerPresenter
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepositoryContract.Repository


class MainPresenter(view: View, repository: Repository) :
    RecyclerPresenter<Task, Holder, View, Repository>(view, repository), Presenter {

    override fun bindHolder(holder: Holder) {
        with(holder) {
            data[listPosition].apply {

                setTitle(title ?: DEFAULT_TITLE)
                setDescription(description ?: DEFAULT_DESCRIPTION)
                setChecked(isDone())
                setImportanceColor(Color.parseColor(priority?.color ?: DEFAULT_PRIORITY_COLOR))

                (category?.name ?: DEFAULT_CATEGORY_NAME).apply {
                    if (listPosition > 0) {
                        if (data[listPosition - 1].category?.id != category?.id) showHeader(this)
                        else hideHeader()
                    } else showHeader(this)
                }

            }
        }
    }

    override fun handleData(data: ArrayList<Task>) {
        super.handleData(ArrayList(data.sortedWith(compareBy<Task, Int?>(nullsLast()) { it.category?.id })))
    }

    override fun toggleItem(holder: Holder?) {
        holder?.apply {
            with(data[listPosition]) {
                toggleDone()
                repository.edit(this).addSuccessListener {
                    view.showMessage(MainValues.TASK_EDIT_SUCCESS_MESSAGE)
                }.addFailureListener {
                    toggleDone()
                    setChecked(isDone())
                    view.showMessage(it)
                }.start()
            }
        }
    }


}