package ru.tsu.ibrahimfall.notforget.app.fragments.main

import ru.tsu.ibrahimfall.notforget.model.items.task.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepositoryContract
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepositoryContract

class MainRepositoryManager(
    private val tasksRepository: TasksRepositoryContract.Repository,
    private val authRepository: AuthRepositoryContract.Repository
) : MainContract.Repository {

    override fun edit(task: Task): MvpBaseContract.Callback<Boolean> = tasksRepository.edit(task)


    override fun logout(): MvpBaseContract.Callback<Boolean> = authRepository.logout()

    override fun getData(): MvpBaseContract.Callback<ArrayList<Task>> = tasksRepository.getData()

    override fun stopListening() {
        tasksRepository.stopListening()
        authRepository.stopListening()
    }


}