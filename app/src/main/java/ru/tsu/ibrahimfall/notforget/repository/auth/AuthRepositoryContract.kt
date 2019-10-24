package ru.tsu.ibrahimfall.notforget.repository.auth

import ru.tsu.ibrahimfall.notforget.model.auth.LoginCredentials
import ru.tsu.ibrahimfall.notforget.model.auth.Token
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback

interface AuthRepositoryContract {

    interface Repository : MvpBaseContract.Repository {
        fun login(credentials: LoginCredentials): Callback<Boolean>
        fun logout(): Callback<Boolean>
    }

    interface Preferences {
        fun setLoggedIn(boolean: Boolean)
        fun isLoggedIn(): Boolean
        fun setToken(token: Token?)
        fun getToken(): Token?
    }

}