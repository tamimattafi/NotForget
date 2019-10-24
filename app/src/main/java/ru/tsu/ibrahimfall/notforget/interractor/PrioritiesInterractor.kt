package ru.tsu.ibrahimfall.notforget.interractor

import retrofit2.Call
import retrofit2.http.GET
import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_PRIORITIES

interface PrioritiesInterractor {

    @GET(PATH_PRIORITIES)
    fun getAll(): Call<ArrayList<Priority>>

}