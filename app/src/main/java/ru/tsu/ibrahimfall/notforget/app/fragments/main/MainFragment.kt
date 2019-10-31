package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.Application
import ru.tsu.ibrahimfall.notforget.app.custom.dialogs.ConfirmationDialog
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Actions
import ru.tsu.ibrahimfall.notforget.app.fragments.login.LoginFragment
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.*
import ru.tsu.ibrahimfall.notforget.app.fragments.main.creation.CreationFragment
import ru.tsu.ibrahimfall.notforget.app.fragments.main.details.DetailsFragment
import ru.tsu.ibrahimfall.notforget.interactor.auth.AuthInteractor
import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepository
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepository
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showSnackBar

class MainFragment : NavigationFragment(), View {

    lateinit var adapter: Adapter
    lateinit var presenter: Presenter

    override var layoutId: Int = R.layout.fragment_main
    override var name: String = "fragment-name"

    private val logoutDialog by lazy {
        ConfirmationDialog(activity!!).apply {
            title = context!!.resources.getString(R.string.logout_confirmation_title)
        }.setConfirmationListener {
            presenter.logout()
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        MainRepositoryManager(

            TasksRepository(TasksInteractor.getInstance()),
            AuthRepository(AuthInteractor.getInstance(), Application.getAuthPreferences())

        ).also { presenter = MainPresenter(this, it) }

        adapter = MainAdapter(this)

        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, layoutManager!!.layoutDirection))
            adapter = this@MainFragment.adapter
        }

        presenter.loadData()

        fab.setOnClickListener {
            navigationManager.attachFragment(CreationFragment())
        }

        refresh.setOnRefreshListener {
            presenter.refresh()
            refresh.isRefreshing = false
        }

        logout.setOnClickListener {
            logoutDialog.show()
        }
    }

    override fun showMessage(message: String) {
        fragmentView.showSnackBar(message)
    }

    override fun bindHolder(holder: Holder) {
        presenter.bindHolder(holder)
    }

    override fun getAdapter(): MvpBaseContract.Adapter = adapter

    override fun onHolderClick(holder: Holder) {
        presenter.onItemClick(holder)
    }

    override fun onHolderAction(holder: Holder?, action: Int) {
        if (action == Actions.ACTION_TOGGLE) {
            presenter.toggleItem(holder)
        }
    }

    override fun openTaskDetails(taskId: Int) {
        navigationManager.attachFragment(DetailsFragment(taskId))
    }


    override fun onSelected() {
        if (::presenter.isInitialized && MainValues.shouldRefresh) {
            MainValues.shouldRefresh = false
            presenter.refresh()
        }
    }

    override fun onLogout() {
        navigationManager.attachBaseFragment(LoginFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

}