package ru.tsu.ibrahimfall.notforget.app.fragments.login

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_login.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.Application
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Visibility
import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginContract.Presenter
import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginContract.View
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainFragment
import ru.tsu.ibrahimfall.notforget.interactor.auth.AuthInteractor
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepository
import ru.tsu.ibrahimfall.notforget.utils.AppUtils
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showSnackBar
import ru.tsu.ibrahimfall.notforget.utils.KeyboardUtils.hideKeyboard


class LoginFragment : NavigationFragment(), View {

    override var layoutId: Int = R.layout.fragment_login
    override var name: String = "fragment-login"

    private lateinit var presenter: Presenter

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AuthRepository(
            AuthInteractor.getInstance(),
            Application.getAuthPreferences()
        ).let { repository ->
            presenter = LoginPresenter(this, repository)
        }

        login.setOnClickListener {
            activity!!.hideKeyboard()
            login.isEnabled = false
            loading.visibility = Visibility.VISIBLE
            presenter.login()
        }
    }

    override fun onLoginError(message: String) {
        login.isEnabled = true
        loading.visibility = Visibility.GONE
        fragmentView.showSnackBar(message, AppUtils.getColor(context!!, R.color.colorRed))
    }

    override fun onLoginSuccess() {
        navigationManager.attachBaseFragment(MainFragment())
    }

    override fun getEmail(): String = email.text.toString()

    override fun getPassword(): String = password.text.toString()

    override fun showMessage(message: String) {
        fragmentView.showSnackBar(message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

}