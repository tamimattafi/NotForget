package ru.tsu.ibrahimfall.notforget.app.fragments.login

import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

interface LoginContract {

    interface View : MvpBaseContract.View {
        fun onLoginError(message: String)
        fun onLoginSuccess()
        fun getEmail(): String
        fun getPassword(): String
    }

    interface Presenter : MvpBaseContract.Presenter {
        fun login()
    }
}