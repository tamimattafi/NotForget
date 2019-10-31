package ru.tsu.ibrahimfall.notforget.app.fragments.main.creation

import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.model.items.task.NewTask
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.repository.categories.CategoryRepositoryContract
import ru.tsu.ibrahimfall.notforget.repository.priorities.PrioritiesRepositoryContract
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepositoryContract

class CreationRepositoryManager(
    private val taskRepository: TasksRepositoryContract.Repository,
    private val categoryRepository: CategoryRepositoryContract.Repository,
    private val priorityRepository: PrioritiesRepositoryContract.Repository
) : CreationContract.Repository {

    override fun createTask(task: NewTask): MvpBaseContract.Callback<Boolean> =
        taskRepository.post(task)


    override fun getCategories(): MvpBaseContract.Callback<ArrayList<Category>> =
        categoryRepository.getData()


    override fun getPriorities(): MvpBaseContract.Callback<ArrayList<Priority>> =
        priorityRepository.getData()


    override fun stopListening() {
        taskRepository.stopListening()
        categoryRepository.stopListening()
        priorityRepository.stopListening()
    }


}