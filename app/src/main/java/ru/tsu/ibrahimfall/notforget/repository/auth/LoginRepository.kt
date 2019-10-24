package ru.tsu.ibrahimfall.notforget.repository.auth

import ru.tsu.ibrahimfall.notforget.interractor.AuthInterractor
import ru.tsu.ibrahimfall.notforget.model.auth.LoginCredentials
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.repositories.BaseApiRepository

class LoginRepository(
    private val interractor: AuthInterractor,
    private val preferences: AuthRepositoryContract.Preferences
) : BaseApiRepository(), AuthRepositoryContract.Repository {

    override fun login(credentials: LoginCredentials): Callback<Boolean> =
        handleCustomCall(interractor.login(credentials)) { notification, token ->

            preferences.apply {
                setToken(token)
                setLoggedIn(true)
            }

            notification.notifySuccess(true)

        }

    override fun logout(): Callback<Boolean> = createCallback { notification ->

        preferences.run {
            setLoggedIn(false)
            setToken(null)
        }

        notification.notifySuccess(true)
    }


}