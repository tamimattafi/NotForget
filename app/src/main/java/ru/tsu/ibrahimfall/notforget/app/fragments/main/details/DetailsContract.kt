package ru.tsu.ibrahimfall.notforget.app.fragments.main.details

import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract.TaskDetails
import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract.TaskMoreDetails
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

interface DetailsContract {

    interface View : MvpBaseContract.View, TaskDetails, TaskMoreDetails {
        fun onTaskLoaded()
        fun onTaskLoadingError(message: String)
    }

    interface Presenter : MvpBaseContract.Presenter {
        fun loadTask(taskId: Int)
    }

}