package ru.tsu.ibrahimfall.notforget.repository.categories

import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

interface CategoryRepositoryContract {

    interface Repository : ListRepository<Category> {
        fun post(category: Category): Callback<Boolean>
    }
}