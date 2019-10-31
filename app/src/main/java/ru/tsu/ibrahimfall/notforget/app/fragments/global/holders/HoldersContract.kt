package ru.tsu.ibrahimfall.notforget.app.fragments.global.holders

import androidx.annotation.ColorInt
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

interface HoldersContract {

    interface TaskHolder : MvpBaseContract.Holder, TaskDetails {
        fun hideCategory()
    }

    interface TaskDetails {
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setImportanceColor(@ColorInt color: Int)
        fun setIsDone(isDone: Boolean)
        fun setCategory(category: String)
    }

    interface TaskMoreDetails {
        fun setCreationDate(date: String)
        fun setPriority(priority: String)
    }

    interface TextHolder : MvpBaseContract.Holder {
        fun setText(text: String)
        fun getText(): String
    }
}