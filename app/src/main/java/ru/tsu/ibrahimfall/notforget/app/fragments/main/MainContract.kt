package ru.tsu.ibrahimfall.notforget.app.fragments.main

import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.model.items.task.Task
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.adapters.RecyclerAdapter
import ru.tsu.ibrahimfall.notforget.mvp.navigation.global.NavigationContract

interface MainContract {

    interface View : ListenerView<Holder>, NavigationContract.SelectionListener {
        fun openTaskDetails(taskId: Int)
        fun onLogout()
    }

    interface Presenter : RecyclerPresenter<Holder> {
        fun toggleItem(holder: Holder?)
        fun onItemClick(holder: Holder)
        fun logout()
    }

    interface Holder : HoldersContract.TaskHolder

    interface Repository : ListRepository<Task> {
        fun edit(task: Task): Callback<Boolean>
        fun logout(): Callback<Boolean>
    }

    abstract class Adapter(view: View) : RecyclerAdapter<Holder>(view)
}