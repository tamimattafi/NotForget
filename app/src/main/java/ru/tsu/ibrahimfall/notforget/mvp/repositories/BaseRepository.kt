package ru.tsu.ibrahimfall.notforget.mvp.repositories

import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.callbacks.ActionCallback


abstract class BaseRepository : Repository {

    private val callbacks: ArrayList<Callback<*>> by lazy { ArrayList<Callback<*>>() }

    override fun stopListening() {
        callbacks.forEach {
            it.cancel()
        }
        callbacks.clear()
    }

    fun <T> createCallback(action: (callback: NotificationCallback<T>) -> Unit): Callback<T> =
        ActionCallback<T>().apply {

            setAction(action)
            callbacks.add(this)

            addSuccessListener {
                callbacks.remove(this)
            }

            addFailureListener {
                callbacks.remove(this)
            }

        }

}