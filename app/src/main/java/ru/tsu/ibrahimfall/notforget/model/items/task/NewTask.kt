package ru.tsu.ibrahimfall.notforget.model.items.task

import com.google.gson.annotations.SerializedName


data class NewTask(
    var title: String? = null,
    var description: String? = null,
    var done: Int = 0,
    val deadline: Int = 0,
    @SerializedName(CATEGORY_SERIALIZATION)
    var category: Int? = null,
    @SerializedName(PRIORITY_SERIALIZATION)
    var priority: Int? = null
) {
    companion object {
        const val CATEGORY_SERIALIZATION = "category_id"
        const val PRIORITY_SERIALIZATION = "priority_id"

    }
}



