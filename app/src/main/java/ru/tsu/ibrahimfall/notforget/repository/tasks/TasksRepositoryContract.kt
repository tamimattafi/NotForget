package ru.tsu.ibrahimfall.notforget.repository.tasks

import ru.tsu.ibrahimfall.notforget.model.items.task.NewTask
import ru.tsu.ibrahimfall.notforget.model.items.task.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.ListRepository

interface TasksRepositoryContract {

    interface Repository : ListRepository<Task> {
        fun post(task: NewTask): Callback<Boolean>
        fun get(id: Int): Callback<Task>
        fun edit(task: Task): Callback<Boolean>
        fun delete(id: Int): Callback<Boolean>
    }


}