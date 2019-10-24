package ru.tsu.ibrahimfall.notforget.repository.priorities

import ru.tsu.ibrahimfall.notforget.interractor.PrioritiesInterractor
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class PrioritiesRepository(private val interractor: PrioritiesInterractor) : BaseApiRepository(),
    PrioritiesRepositoryContract.Repository {

    override fun getData(): Callback<ArrayList<Priority>> = handleBodyCall(interractor.getAll())

}