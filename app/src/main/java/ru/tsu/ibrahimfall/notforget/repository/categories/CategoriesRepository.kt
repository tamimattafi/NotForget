package ru.tsu.ibrahimfall.notforget.repository.categories

import ru.tsu.ibrahimfall.notforget.interractor.CategoriesInterractor
import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class CategoriesRepository(private val interractor: CategoriesInterractor) : BaseApiRepository(),
    CategoryRepositoryContract.Repository {

    override fun post(category: Category): MvpBaseContract.Callback<Boolean> =
        handleActionCall(interractor.post(category))

    override fun getData(): MvpBaseContract.Callback<ArrayList<Category>> =
        handleBodyCall(interractor.getAll())

}