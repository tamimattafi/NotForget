package ru.tsu.ibrahimfall.notforget.interactor.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorBuilder
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_LOGIN
import ru.tsu.ibrahimfall.notforget.model.auth.LoginCredentials
import ru.tsu.ibrahimfall.notforget.model.auth.Token

interface AuthInteractor {

    @POST(PATH_LOGIN)
    fun login(@Body credentials: LoginCredentials): Call<Token>

    companion object {

        private var instance: AuthInteractor? = null

        fun getInstance(): AuthInteractor = instance ?: InteractorBuilder.createSimpleInteractor(
            AuthInteractor::class.java
        ).also { instance = it }
    }

}