package ru.tsu.ibrahimfall.notforget.interactor.global

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthHttpClieant
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthInterceptor

object InteractorBuilder {

    fun getBaseBuilder(): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(InteractorPaths.BASE_LINK)
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> createSimpleInteractor(clazz: Class<T>): T = getBaseBuilder().build().create(clazz)

    fun <T> createAuthInteractor(authInterceptor: AuthInterceptor, clazz: Class<T>): T =
        getBaseBuilder()
            .client(AuthHttpClieant.getInstance(authInterceptor))
            .build()
            .create(clazz)
}