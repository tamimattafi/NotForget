package ru.tsu.ibrahimfall.notforget.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {

    fun Activity.isKeyboardVisible(): Boolean {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
            return InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight").invoke(
                this
            ) as Int > 0
        }
        return false
    }

    fun Activity.hideKeyboard() {
        if (isKeyboardVisible()) {
            currentFocus?.let { v ->
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

    fun Activity.showKeyboard() {
        if (!isKeyboardVisible()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}