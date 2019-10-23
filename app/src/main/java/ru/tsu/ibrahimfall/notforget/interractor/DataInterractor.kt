package ru.tsu.ibrahimfall.notforget.interractor

import retrofit2.Call
import retrofit2.http.GET
import ru.tsu.ibrahimfall.notforget.model.Task

interface DataInterractor {

    @GET("Items/get_tasks")
    fun getTasks(): Call<ArrayList<Task>>
}