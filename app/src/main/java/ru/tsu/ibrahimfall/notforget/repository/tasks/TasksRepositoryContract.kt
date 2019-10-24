package ru.tsu.ibrahimfall.notforget.repository.tasks

import ru.tsu.ibrahimfall.notforget.model.items.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

interface TasksRepositoryContract {

    interface Repository : ListRepository<Task> {
        fun post(task: Task): Callback<Boolean>
        fun get(id: Int): Callback<Task>
        fun edit(task: Task): Callback<Boolean>
        fun delete(id: Int): Callback<Boolean>
    }

}