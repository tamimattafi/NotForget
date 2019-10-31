package ru.tsu.ibrahimfall.notforget.interactor.tasks

import retrofit2.Call
import retrofit2.http.*
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorBuilder
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_ID
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_TASKS
import ru.tsu.ibrahimfall.notforget.interactor.global.InteractorPaths.PATH_TASKS_ID
import ru.tsu.ibrahimfall.notforget.interactor.global.client.AuthInterceptor
import ru.tsu.ibrahimfall.notforget.model.items.task.NewTask
import ru.tsu.ibrahimfall.notforget.model.items.task.Task

interface TasksInteractor {

    @GET(PATH_TASKS)
    fun getAll(): Call<ArrayList<Task>>

    @POST(PATH_TASKS)
    fun post(@Body task: NewTask): Call<Void>

    @PATCH(PATH_TASKS_ID)
    fun get(@Path(PATH_ID) id: Int): Call<Task>

    @PATCH(PATH_TASKS_ID)
    fun edit(@Path(PATH_ID) id: Int, @Body task: Task): Call<Void>

    @DELETE(PATH_TASKS_ID)
    fun delete(@Path(PATH_ID) id: Int): Call<Void>


    companion object {

        private var instance: TasksInteractor? = null

        fun getInstance(authInterceptor: AuthInterceptor): TasksInteractor =
            instance ?: InteractorBuilder.createAuthInteractor(
                authInterceptor,
                TasksInteractor::class.java
            ).also { instance = it }

        fun getInstance(): TasksInteractor = instance!!

    }

}