package ru.tsu.ibrahimfall.notforget.repository.tasks

import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.model.items.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class TasksRepository(private val interactor: TasksInteractor) : BaseApiRepository(),
    TasksRepositoryContract.Repository {

    override fun post(task: Task): Callback<Boolean> = handleActionCall(interactor.post(task))

    override fun get(id: Int): Callback<Task> = handleBodyCall(interactor.get(id))

    override fun edit(task: Task): Callback<Boolean> =
        handleActionCall(interactor.edit(task.id!!, task))

    override fun delete(id: Int): Callback<Boolean> = handleActionCall(interactor.delete(id))

    override fun getData(): Callback<ArrayList<Task>> = handleBodyCall(interactor.getAll())

}