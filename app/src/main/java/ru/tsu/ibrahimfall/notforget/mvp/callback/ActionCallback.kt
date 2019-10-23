package ru.tsu.ibrahimfall.notforget.mvp.callback

import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

open class ActionCallback<T> : MvpBaseContract.ActionCallback<T> {

    protected var onSuccessListeners: ArrayList<(data: T) -> Unit> = ArrayList()
    protected var onFailureListeners: ArrayList<(message: String) -> Unit> = ArrayList()

    private var action: ((callback: MvpBaseContract.ActionCallback<T>) -> Unit)? = null

    override fun addSuccessListener(onSuccess: (data: T) -> Unit): Callback<T> =
        this.also { it.onSuccessListeners.add(onSuccess) }

    override fun addFailureListener(onFailure: (message: String) -> Unit): Callback<T> =
        this.also { it.onFailureListeners.add(onFailure) }

    override fun setAction(action: (callback: MvpBaseContract.ActionCallback<T>) -> Unit): Callback<T> =
        this.also { it.action = action }

    override fun start() {
        action?.invoke(this)
            ?: onFailureListeners.forEach { it.invoke(CallbackConstants.ACTION_ERROR) }
    }

    override fun notifySuccess(data: T) {
        this.onSuccessListeners.forEach {
            it.invoke(data)
        }
    }

    override fun notifyFailure(message: String) {
        this.onFailureListeners.forEach {
            it.invoke(message)
        }
    }

    override fun cancel() {
        onFailureListeners.clear()
        onSuccessListeners.clear()
        action = null
    }

}