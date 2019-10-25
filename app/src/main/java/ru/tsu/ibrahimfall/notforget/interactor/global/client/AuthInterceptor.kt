package ru.tsu.ibrahimfall.notforget.interactor.global.client

import okhttp3.Interceptor
import okhttp3.Response
import ru.tsu.ibrahimfall.notforget.mvp.interactor.InteractorConstants
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepositoryContract


class AuthInterceptor(private val authPreferences: AuthRepositoryContract.Preferences) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        with(authPreferences) {
            val initialRequest = chain.request()
            return if (isLoggedIn() && getToken() != null) {
                val modifiedRequest = initialRequest.newBuilder()
                    .addHeader(
                        InteractorConstants.HEADER_AUTH,
                        InteractorConstants.HEADER_AUTH_BARER + " " + getToken()!!.value
                    )
                    .build()

                chain.proceed(modifiedRequest)
            } else chain.proceed(initialRequest)
        }
    }

}