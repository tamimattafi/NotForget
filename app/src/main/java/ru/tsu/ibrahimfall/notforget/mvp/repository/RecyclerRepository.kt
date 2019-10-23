package ru.tsu.ibrahimfall.notforget.mvp.repository

import retrofit2.Call
import retrofit2.Response
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

abstract class RecyclerRepository<T> : BaseRepository(), MvpBaseContract.RecyclerRepository<T> {

    override fun getData(): Callback<ArrayList<T>> = createCallback { notification ->
        with(notification) {

            getDataCall().enqueue(object : retrofit2.Callback<ArrayList<T>> {
                override fun onFailure(call: Call<ArrayList<T>>, t: Throwable) {
                    notifyFailure(
                        t.localizedMessage ?: t.message ?: RepositoryConstants.UNKNOW_ERROR
                    )
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ArrayList<T>>,
                    response: Response<ArrayList<T>>
                ) {
                    response.body()?.let { notifySuccess(it) }
                        ?: notifyFailure(RepositoryConstants.NULL_BODY_ERROR)
                }
            })

        }
    }

    abstract fun getDataCall(): Call<ArrayList<T>>

}