package ru.tsu.ibrahimfall.notforget.model.items.task

import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.model.items.Priority

data class Task(
    val id: Int,
    var title: String? = null,
    var description: String? = null,
    var done: Int = 0,
    val deadline: Long = 0,
    var category: Category? = null,
    var priority: Priority? = null,
    val created: Long = 0
) {

    fun isDone(): Boolean = (done == 1)

    fun setDone(done: Boolean) {
        this.done = if (done) 1
        else 0
    }

    fun toggleDone() {
        setDone(!isDone())
    }

    companion object {

        const val DEFAULT_TITLE = "Unnamed"
        const val DEFAULT_DESCRIPTION = "No description"
        const val DEFAULT_PRIORITY_COLOR = "#F5884A"
        const val DEFAULT_CATEGORY_NAME = "Unnamed Category"
        const val DEFAULT_PRIORITY_NAME = " ~ "

        const val TASK_EDIT_SUCCESS_MESSAGE = "Task was successfully edited."

        const val DATE_PATTERN = "dd.MM.yyyy"
    }

}
