package ru.tsu.ibrahimfall.notforget.repository.auth

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.tsu.ibrahimfall.notforget.model.auth.Token
import ru.tsu.ibrahimfall.notforget.repository.auth.AuthRepositoryContract.Preferences

class AuthPreferences(private val sharedPreferences: SharedPreferences) : Preferences {

    override fun setLoggedIn(boolean: Boolean) = with(sharedPreferences.edit()) {
        putBoolean(LOGIN_KEY, boolean)
        apply()
    }

    override fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(LOGIN_KEY, false)


    override fun setToken(token: Token?) = with(sharedPreferences.edit()) {
        putString(TOKEN_KEY, Gson().toJson(token))
        apply()
    }

    override fun getToken(): Token? =
        Gson().fromJson<Token?>(sharedPreferences.getString(TOKEN_KEY, null), Token::class.java)


    companion object {
        const val LOGIN_KEY = "login-key"
        const val TOKEN_KEY = "token-key"
        const val PREFERENCES_NAME = "auth-preferences"
    }

}