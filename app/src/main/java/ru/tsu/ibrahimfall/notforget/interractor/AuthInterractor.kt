package ru.tsu.ibrahimfall.notforget.interractor

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.tsu.ibrahimfall.notforget.model.auth.LoginCredentials
import ru.tsu.ibrahimfall.notforget.model.auth.Token
import ru.tsu.ibrahimfall.notforget.interractor.global.ApiPaths.PATH_LOGIN

interface AuthInterractor {

    @POST(PATH_LOGIN)
    fun login(@Body credentials: LoginCredentials): Call<Token>

}