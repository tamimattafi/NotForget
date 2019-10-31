package ru.tsu.ibrahimfall.notforget.app.fragments.global.holders

import androidx.annotation.ColorInt
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

interface HoldersContract {


    interface TaskHolder : MvpBaseContract.Holder {
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setImportanceColor(@ColorInt color: Int)
        fun setChecked(checked: Boolean)
        fun showHeader(header: String)
        fun hideHeader()
    }

    interface TextHolder : MvpBaseContract.Holder {
        fun setText(text: String)
        fun getText(): String
    }
}