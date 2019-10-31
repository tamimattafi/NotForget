package ru.tsu.ibrahimfall.notforget.app.fragments.main.creation

import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.app.fragments.main.creation.CreationContract.*
import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.model.items.task.NewTask
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.presenters.BasePresenter

class CreationPresenter(view: View, repository: Repository) :
    BasePresenter<View, Repository>(view, repository), Presenter {

    private val task: NewTask = NewTask()
    private val priorities: ArrayList<Priority> = ArrayList()
    private val categories: ArrayList<Category> = ArrayList()

    override fun loadTaskSections() {
        with(repository) {
            getCategories().mapCallbackSize(categories) {
                view.onCategoriesPrepared(it)
            }

            getPriorities().mapCallbackSize(priorities) {
                view.onPrioritiesPrepared(it)
            }
        }
    }

    private fun <T> MvpBaseContract.Callback<ArrayList<T>>.mapCallbackSize(
        baseList: ArrayList<T>,
        onSuccess: (size: Int) -> Unit
    ) {

        this.addSuccessListener {

            with(baseList) {
                clear()
                addAll(it)
            }

            onSuccess.invoke(baseList.size)

        }.addFailureListener {
            view.showMessage(it)
        }.start()

    }

    override fun createTask() {
        with(view) {

            task.apply {

                title = getTitle()
                description = getDescription()

                repository.createTask(this).addSuccessListener {
                    onTaskCreated()
                }.addFailureListener {
                    showMessage(it)
                }.start()
            }
        }
    }

    override fun onCategorySelected(position: Int) {
        task.category = categories[position].id
    }

    override fun onPrioritySelected(position: Int) {
        task.priority = priorities[position].id
    }

    override fun bindCategory(holder: HoldersContract.TextHolder) {
        holder.setText(categories[holder.listPosition].name)
    }

    override fun bindPriority(holder: HoldersContract.TextHolder) {
        holder.setText(priorities[holder.listPosition].name)
    }


}