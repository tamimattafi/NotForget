package ru.tsu.ibrahimfall.notforget.interactor.priorities

import retrofit2.Call
import retrofit2.http.GET
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorBuilder
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_PRIORITIES
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthInterceptor
import ru.tsu.ibrahimfall.notforget.model.items.Priority

interface PrioritiesInteractor {

    @GET(PATH_PRIORITIES)
    fun getAll(): Call<ArrayList<Priority>>


    companion object {

        private var instance: PrioritiesInteractor? = null

        fun getInstance(authInterceptor: AuthInterceptor): PrioritiesInteractor =
            instance ?: InteractorBuilder.createAuthInteractor(
                authInterceptor,
                PrioritiesInteractor::class.java
            ).also { instance = it }

        fun getInstance(): PrioritiesInteractor = instance!!

    }
}