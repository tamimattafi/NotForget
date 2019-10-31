package ru.tsu.ibrahimfall.notforget.model.auth

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName(VALUE_SERIALIZATION) val value: String) {
    companion object {
        const val VALUE_SERIALIZATION = "api_token"
    }
}
