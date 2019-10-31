package ru.tsu.ibrahimfall.notforget.app.fragments.main.creation

import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.model.items.task.NewTask
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback

interface CreationContract {

    interface View : MvpBaseContract.View, TaskData, TaskSections {
        fun onTaskCreated()
    }

    interface TaskData {
        fun getTitle(): String
        fun getDescription(): String
    }

    interface TaskSections {
        fun onCategoriesPrepared(dataCount: Int)
        fun onPrioritiesPrepared(dataCount: Int)
    }


    interface Presenter : MvpBaseContract.Presenter {
        fun loadTaskSections()
        fun createTask()
        fun onCategorySelected(position: Int)
        fun onPrioritySelected(position: Int)
        fun bindCategory(holder: HoldersContract.TextHolder)
        fun bindPriority(holder: HoldersContract.TextHolder)
    }

    interface Repository : MvpBaseContract.Repository {
        fun createTask(task: NewTask): Callback<Boolean>
        fun getCategories(): Callback<ArrayList<Category>>
        fun getPriorities(): Callback<ArrayList<Priority>>
    }


}