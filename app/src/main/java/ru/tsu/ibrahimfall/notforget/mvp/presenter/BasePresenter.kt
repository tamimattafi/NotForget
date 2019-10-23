package ru.tsu.ibrahimfall.notforget.mvp.presenter

import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

abstract class BasePresenter<V : View, R : Repository>(
    protected val view: V,
    protected val repository: R
) : Presenter {

    override fun onDestroyView() {
        repository.stopListening()
    }

}