package ru.tsu.ibrahimfall.notforget.mvp.repositories

import retrofit2.Call
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.Callback
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.NotificationCallback
import ru.tsu.ibrahimfall.notforget.mvp.callbacks.ApiCallback

open class BaseApiRepository : BaseRepository() {

    private val calls: ArrayList<Pair<ApiCallback<*, *>, Call<*>>> = ArrayList()

    fun handleActionCall(call: Call<Void>): Callback<Boolean> =
        createCallback { notification ->
            call.handleCallback(notification) {
                notification.notifySuccess(true)
            }
        }

    fun <T, R> handleCustomBodyCall(
        call: Call<T>,
        action: (notification: NotificationCallback<R>, data: T) -> Unit
    ): Callback<R> = createCallback { notification ->
        call.handleCallback(notification) { body ->

            body?.let {
                action(notification, it)
            } ?: notification.notifyFailure(RepositoryConstants.NULL_BODY_ERROR)

        }
    }

    fun <T> handleBodyCall(call: Call<T>): Callback<T> =
        handleCustomBodyCall(call) { notification, data ->
            notification.notifySuccess(data)
        }


    private fun <T, R> Call<T>.handleCallback(
        notification: NotificationCallback<R>,
        onSuccess: (data: T?) -> Unit
    ): ApiCallback<T, R> = ApiCallback<T, R>(notification, onSuccess).also {

        val element = Pair(it, this)
        calls.add(element)
        it.addOnCompleteListener {
            calls.remove(element)
        }

        this.enqueue(it)
        }

    override fun stopListening() {
        super.stopListening()

        calls.forEach {
            with(it) {
                first.cancel()
                second.cancel()
            }
        }

        calls.clear()
    }

}