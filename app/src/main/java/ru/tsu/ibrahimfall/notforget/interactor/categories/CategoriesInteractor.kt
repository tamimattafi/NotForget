package ru.tsu.ibrahimfall.notforget.interactor.categories

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorBuilder
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_CATEGORIES
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthInterceptor
import ru.tsu.ibrahimfall.notforget.model.items.Category


interface CategoriesInteractor {

    @GET(PATH_CATEGORIES)
    fun getAll(): Call<ArrayList<Category>>

    @POST(PATH_CATEGORIES)
    fun post(@Body category: Category): Call<Void>

    companion object {

        private var instance: CategoriesInteractor? = null

        fun getInstance(authInterceptor: AuthInterceptor): CategoriesInteractor =
            instance ?: InteractorBuilder.createAuthInteractor(
                authInterceptor,
                CategoriesInteractor::class.java
            ).also { instance = it }

        fun getInstance(): CategoriesInteractor = instance!!

    }

}