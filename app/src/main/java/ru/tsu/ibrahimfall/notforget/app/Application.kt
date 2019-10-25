package ru.tsu.ibrahimfall.notforget.app

import android.app.Application
import android.content.Context
import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginFragment
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainFragment
import ru.tsu.ibrahimfall.notforget.interactor.auth.AuthInteractor
import ru.tsu.ibrahimfall.notforget.interactor.categories.CategoriesInteractor
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthInterceptor
import ru.tsu.ibrahimfall.notforget.interactor.priorities.PrioritiesInteractor
import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthPreferences
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepositoryContract

class Application : Application() {


    override fun onCreate() {
        super.onCreate()

        authPreferences = AuthPreferences(
            this.getSharedPreferences(
                AuthPreferences.PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        )

        AuthInterceptor(authPreferences).apply {
            AuthInteractor.getInstance()
            TasksInteractor.getInstance(this)
            CategoriesInteractor.getInstance(this)
            PrioritiesInteractor.getInstance(this)
        }

    }


    companion object {

        private lateinit var authPreferences: AuthRepositoryContract.Preferences

        fun getLaunchFragment(): NavigationFragment =
            if (authPreferences.isLoggedIn()) MainFragment() else LoginFragment()

        fun getAuthPreferences(): AuthRepositoryContract.Preferences = authPreferences

    }



}