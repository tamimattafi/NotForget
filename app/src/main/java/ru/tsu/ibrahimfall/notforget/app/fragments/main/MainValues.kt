package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.content.res.Resources
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.empty.Placeholder

object MainValues {

    var shouldRefresh: Boolean = false

    fun getPlaceholder(resources: Resources): Placeholder = Placeholder(
        resources.getString(R.string.no_tasks_to_show),
        R.drawable.placeholder
    )

}
