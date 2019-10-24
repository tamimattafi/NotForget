package ru.tsu.ibrahimfall.notforget.interractor

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import ru.tsu.ibrahimfall.notforget.model.items.Task
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_ID
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_TASKS
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_TASKS_ID

interface TasksInterractor {

    @GET(PATH_TASKS)
    fun getAll(): Call<ArrayList<Task>>

    @POST(PATH_TASKS)
    fun post(@Body task: Task): Call<ResponseBody>

    @PATCH(PATH_TASKS_ID)
    fun get(@Path(PATH_ID) id: Int): Call<Task>

    @PATCH(PATH_TASKS_ID)
    fun edit(@Path(PATH_ID) id: Int, @Body task: Task): Call<ResponseBody>

    @DELETE(PATH_TASKS_ID)
    fun delete(@Path(PATH_ID) id: Int): Call<ResponseBody>

}