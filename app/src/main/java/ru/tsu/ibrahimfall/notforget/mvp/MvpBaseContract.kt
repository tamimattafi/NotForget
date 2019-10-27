package ru.tsu.ibrahimfall.notforget.mvp



interface MvpBaseContract {

    interface Repository {
        fun stopListening()
    }

    interface ListRepository<T> : Repository {
        fun getData(): Callback<ArrayList<T>>
    }

    interface Callback<T> {
        fun addSuccessListener(onSuccess: (data: T) -> Unit): Callback<T>
        fun addFailureListener(onFailure: (message: String) -> Unit): Callback<T>
        fun addCompleteListener(onComplete: () -> Unit): Callback<T>
        fun start()
        fun cancel()
    }

    interface ActionCallback<T> : Callback<T>, NotificationCallback<T> {
        fun setAction(action: (callback: ActionCallback<T>) -> Unit): Callback<T>
    }

    interface NotificationCallback<T> {
        fun notifySuccess(data: T)
        fun notifyFailure(message: String)
        fun notifyComplete()
    }

    interface Presenter {
        fun onDestroyView()
    }

    interface RecyclerPresenter<HOLDER : Holder> : Presenter {
        fun bindHolder(holder: HOLDER)
        fun loadData()
        fun refresh()
    }

    interface Holder {
        var listPosition: Int
        var listener: AdapterListener?
    }

    interface View {
        fun showMessage(message: String)
    }

    interface ListenerView<HOLDER : Holder> : View, AdapterListener {
        fun bindHolder(holder: HOLDER)
        fun getAdapter(): Adapter
    }

    interface Adapter {
        var isLoading: Boolean
        fun setDataCount(dataCount: Int)
    }

    interface AdapterListener {
        fun onHolderClick(listPosition: Int)
        fun onHolderAction(listPosition: Int, action: Int)
    }
}