package ru.tsu.ibrahimfall.notforget.app.fragments.main

import android.content.res.Resources
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.mvp.adapters.holders.empty.Placeholder

object MainValues {

    var shouldRefresh: Boolean = false

    const val DEFAULT_TITLE = "Unnamed"
    const val DEFAULT_DESCRIPTION = "No description"
    const val DEFAULT_PRIORITY_COLOR = "#F5884A"
    const val DEFAULT_CATEGORY_NAME = "Unnamed Category"
    const val TASK_EDIT_SUCCESS_MESSAGE = "Task was successfully edited."

    fun getPlaceholder(resources: Resources): Placeholder = Placeholder(
        resources.getString(R.string.no_tasks_to_show),
        R.drawable.placeholder
    )

}
