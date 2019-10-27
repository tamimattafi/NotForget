package ru.tsu.ibrahimfall.notforget.model.items

data class Task(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val done: Int = 0,
    val deadline: Int = 0,
    val category: Category? = null,
    val priority: Priority? = null,
    val created: Int = 0
)
