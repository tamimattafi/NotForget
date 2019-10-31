package ru.tsu.ibrahimfall.notforget.app.fragments.main

import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.adapters.RecyclerAdapter
import ru.tsu.ibrahimfall.notforget.mvp.navigation.global.NavigationContract

interface MainContract {

    interface View : MvpBaseContract.ListenerView<Holder>, NavigationContract.SelectionListener

    interface Presenter : MvpBaseContract.RecyclerPresenter<Holder> {
        fun toggleItem(holder: Holder?)
    }

    interface Holder : HoldersContract.TaskHolder

    abstract class Adapter(view: View) : RecyclerAdapter<Holder>(view)
}