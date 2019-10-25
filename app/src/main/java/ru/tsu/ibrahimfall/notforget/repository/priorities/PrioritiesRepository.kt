package ru.tsu.ibrahimfall.notforget.repository.priorities

import ru.tsu.ibrahimfall.notforget.interactor.priorities.PrioritiesInteractor
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class PrioritiesRepository(private val interactor: PrioritiesInteractor) : BaseApiRepository(),
    PrioritiesRepositoryContract.Repository {

    override fun getData(): Callback<ArrayList<Priority>> = handleBodyCall(interactor.getAll())

}