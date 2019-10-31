package ru.tsu.ibrahimfall.notforget.app.fragments.main.details

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_details.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.fragments.global.Visibility
import ru.tsu.ibrahimfall.notforget.app.fragments.main.details.DetailsContract.Presenter
import ru.tsu.ibrahimfall.notforget.app.fragments.main.details.DetailsContract.View
import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepository
import ru.tsu.ibrahimfall.notforget.utils.AppUtils
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showSnackBar

class DetailsFragment(private val taskId: Int) : NavigationFragment(), View {

    lateinit var presenter: Presenter

    override var layoutId: Int = R.layout.fragment_details
    override var name: String = "fragment-details"

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = DetailsPresenter(this, TasksRepository(TasksInteractor.getInstance()))

        back.setOnClickListener {
            navigationManager.requestBackPress()
        }

        presenter.loadTask(taskId)
    }

    override fun onTaskLoaded() {
        hideLoading()
        layout.visibility = Visibility.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = Visibility.GONE
    }

    override fun onTaskLoadingError(message: String) {
        hideLoading()
        error.apply {
            text = message
            visibility = Visibility.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        view!!.showSnackBar(message)
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }

    override fun setDescription(description: String) {
        this.description.text = description
    }

    override fun setImportanceColor(color: Int) {
        this.priorityCard.setCardBackgroundColor(color)
    }

    override fun setIsDone(isDone: Boolean) {
        with(done) {
            context.resources.apply {
                text = if (isDone) {
                    setTextColor(AppUtils.getColor(context!!, R.color.colorGreenLight))
                    getString(R.string.done)
                } else {
                    setTextColor(AppUtils.getColor(context!!, R.color.colorRedLight))
                    getString(R.string.not_done)
                }
            }
        }
    }

    override fun setCategory(category: String) {
        this.category.text = category
    }

    override fun setCreationDate(date: String) {
        this.date.text = date
    }

    override fun setPriority(priority: String) {
        this.priority.text = priority
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }


}