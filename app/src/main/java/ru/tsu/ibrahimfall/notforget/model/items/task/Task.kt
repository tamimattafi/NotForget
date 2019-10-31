package ru.tsu.ibrahimfall.notforget.model.items.task

import ru.tsu.ibrahimfall.notforget.model.items.Category
import ru.tsu.ibrahimfall.notforget.model.items.Priority

data class Task(
    val id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var done: Int = 0,
    val deadline: Int = 0,
    var category: Category? = null,
    var priority: Priority? = null,
    val created: Int = 0
) {

    fun isDone(): Boolean = (done == 1)

    fun setDone(done: Boolean) {
        this.done = if (done) 1
        else 0
    }

    fun toggleDone() {
        setDone(!isDone())
    }

}
