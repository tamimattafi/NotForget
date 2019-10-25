package ru.tsu.ibrahimfall.notforget.app.fragments.login

import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginContract.Presenter
import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginContract.View
import ru.tsu.ibrahimfall.notforget.model.auth.LoginCredentials
import ru.tsu.ibrahimfall.notforget.mvp.presenters.BasePresenter
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepositoryContract.Repository

class LoginPresenter(view: View, repository: Repository) :
    BasePresenter<View, Repository>(view, repository), Presenter {

    override fun login() {
        with(view) {
            LoginCredentials(getEmail(), getPassword()).let {
                repository.login(it).addSuccessListener {
                    onLoginSuccess()
                }.addFailureListener { message ->
                    onLoginError(message)
                }.start()
            }
        }
    }

}