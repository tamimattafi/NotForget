package ru.tsu.ibrahimfall.notforget.mvp.repositories

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*
import ru.tsu.ibrahimfall.notforget.mvp.interractor.InterractorConstants

abstract class BaseApiRepository : BaseRepository() {

    fun handleActionCall(call: Call<ResponseBody>): Callback<Boolean> =
        createCallback { notification ->
            notification.run {
                call.enqueue(object : retrofit2.Callback<ResponseBody> {

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        handleError(this@run, t)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (isCodeOk(this@run, response.code())) notifySuccess(true)
                    }

                })
            }
        }

    fun <T, R> handleCustomCall(
        call: Call<T>,
        action: (notification: NotificationCallback<R>, data: T) -> Unit
    ): Callback<R> = createCallback { notification ->
        notification.run {
            call.enqueue(object : retrofit2.Callback<T> {

                override fun onFailure(call: Call<T>, t: Throwable) {
                    handleError(this@run, t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (isCodeOk(this@run, response.code())) {
                        response.body()?.let {
                            action(this@run, it)
                        } ?: notification.notifyFailure(RepositoryConstants.NULL_BODY_ERROR)
                    }
                }

            })
        }
    }

    fun <T> handleBodyCall(call: Call<T>): Callback<T> =
        handleCustomCall(call) { notification, data ->
            notification.notifySuccess(data)
        }

    protected fun handleError(notification: NotificationCallback<*>, t: Throwable) {
        notification.notifyFailure(
            t.localizedMessage ?: t.message ?: RepositoryConstants.UNKNOWN_ERROR
        )
        t.printStackTrace()
    }

    protected fun isCodeOk(notification: NotificationCallback<*>, code: Int): Boolean =
        if (code == InterractorConstants.CODE_OK) true
        else {
            notification.notifyFailure(InterractorConstants.getCodeMessage(code))
            false
        }


}