package ru.tsu.ibrahimfall.notforget.model.auth

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName(API_TOKEN) val value: String) {
    companion object {
        const val API_TOKEN = "api_token"
    }
}
