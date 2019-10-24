package ru.tsu.ibrahimfall.notforget.interractor

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_CATEGORIES
import ru.tsu.ibrahimfall.notforget.model.items.Category


interface CategoriesInterractor {

    @GET(PATH_CATEGORIES)
    fun getAll(): Call<ArrayList<Category>>

    @POST(PATH_CATEGORIES)
    fun post(@Body category: Category): Call<ResponseBody>

}