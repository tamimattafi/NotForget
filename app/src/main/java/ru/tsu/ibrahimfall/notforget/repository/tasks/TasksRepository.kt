package ru.tsu.ibrahimfall.notforget.repository.tasks

import ru.tsu.ibrahimfall.notforget.interractor.TasksInterractor
import ru.tsu.ibrahimfall.notforget.model.items.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class TasksRepository(private val interractor: TasksInterractor) : BaseApiRepository(),
    TasksRepositoryContract.Repository {

    override fun post(task: Task): Callback<Boolean> = handleActionCall(interractor.post(task))

    override fun get(id: Int): Callback<Task> = handleBodyCall(interractor.get(id))

    override fun edit(task: Task): Callback<Boolean> =
        handleActionCall(interractor.edit(task.id!!, task))

    override fun delete(id: Int): Callback<Boolean> = handleActionCall(interractor.delete(id))

    override fun getData(): Callback<ArrayList<Task>> = handleBodyCall(interractor.getAll())

}