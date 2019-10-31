package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Actions
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainContract.*
import ru.tsu.ibrahimfall.notforget.app.fragments.main.creation.CreationFragment
import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepository
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showSnackBar

class MainFragment : NavigationFragment(), View {

    lateinit var adapter: Adapter
    lateinit var presenter: Presenter

    override var layoutId: Int = R.layout.fragment_main
    override var name: String = "fragment-name"

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(
            this,
            TasksRepository(TasksInteractor.getInstance())
        )

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
    }

    override fun showMessage(message: String) {
        fragmentView.showSnackBar(message)
    }

    override fun bindHolder(holder: Holder) {
        presenter.bindHolder(holder)
    }

    override fun getAdapter(): MvpBaseContract.Adapter = adapter

    override fun onHolderClick(holder: Holder) {

    }

    override fun onHolderAction(holder: Holder?, action: Int) {
        if (action == Actions.ACTION_TOGGLE) {
            presenter.toggleItem(holder)
        }
    }


    override fun onSelected() {
        if (::presenter.isInitialized && MainValues.shouldRefresh) {
            MainValues.shouldRefresh = false
            presenter.refresh()
        }
    }


}