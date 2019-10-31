package ru.tsu.ibrahimfall.notforget.interactor.global.client

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object AuthHttpClient {

    const val TIMEOUT_READ = 40L
    const val TIMEOUT_WRITE = 40L
    val UNIT = TimeUnit.SECONDS

    private var instance: OkHttpClient? = null

    fun getInstance(authInterceptor: AuthInterceptor): OkHttpClient =
        instance ?: createInstance(authInterceptor).also { instance = it }

    private fun createInstance(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(TIMEOUT_READ, UNIT)
            .writeTimeout(TIMEOUT_WRITE, UNIT)
            .build()

}