package ru.tsu.ibrahimfall.notforget.repository.categories

import ru.tsu.ibrahimfall.notforget.interactor.categories.CategoriesInteractor
import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class CategoriesRepository(private val interactor: CategoriesInteractor) : BaseApiRepository(),
    CategoryRepositoryContract.Repository {

    override fun post(category: Category): MvpBaseContract.Callback<Boolean> =
        handleActionCall(interactor.post(category))

    override fun getData(): MvpBaseContract.Callback<ArrayList<Category>> =
        handleBodyCall(interactor.getAll())

}