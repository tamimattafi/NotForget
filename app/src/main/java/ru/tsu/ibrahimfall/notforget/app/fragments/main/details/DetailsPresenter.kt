package ru.tsu.ibrahimfall.notforget.app.fragments.main.details

import android.graphics.Color
import ru.tsu.ibrahimfall.notforget.app.fragments.main.details.DetailsContract.Presenter
import ru.tsu.ibrahimfall.notforget.app.fragments.main.details.DetailsContract.View
import ru.tsu.ibrahimfall.notforget.model.items.task.Task
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DATE_PATTERN
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DEFAULT_CATEGORY_NAME
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DEFAULT_DESCRIPTION
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DEFAULT_PRIORITY_COLOR
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DEFAULT_PRIORITY_NAME
import ru.tsu.ibrahimfall.notforget.model.items.task.Task.Companion.DEFAULT_TITLE
import ru.tsu.ibrahimfall.notforget.mvp.presenters.BasePresenter
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepositoryContract.Repository
import ru.tsu.ibrahimfall.notforget.utils.DateUtils

class DetailsPresenter(view: View, repository: Repository) :
    BasePresenter<View, Repository>(view, repository), Presenter {

    override fun loadTask(taskId: Int) {
        repository.get(taskId).addSuccessListener {
            bindTask(it)
            view.onTaskLoaded()
        }.addFailureListener {
            view.onTaskLoadingError(it)
            view.showMessage(it)
        }.start()
    }

    private fun bindTask(task: Task) {
        with(view) {
            task.apply {
                setTitle(title ?: DEFAULT_TITLE)
                setIsDone(isDone())
                setDescription(description ?: DEFAULT_DESCRIPTION)
                setCreationDate(DateUtils.toString(created, DATE_PATTERN))
                setImportanceColor(Color.parseColor(priority?.color ?: DEFAULT_PRIORITY_COLOR))
                setCategory(category?.name ?: DEFAULT_CATEGORY_NAME)
                setPriority(priority?.name ?: DEFAULT_PRIORITY_NAME)
            }
        }
    }

}