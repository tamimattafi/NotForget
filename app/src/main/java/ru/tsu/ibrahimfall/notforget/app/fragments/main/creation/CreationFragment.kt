package ru.tsu.ibrahimfall.notforget.app.fragments.main.creation

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_creation.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.custom.dialogs.ConfirmationDialog
import ru.tsu.ibrahimfall.notforget.app.custom.dialogs.DialogContract.BaseDialog
import ru.tsu.ibrahimfall.notforget.app.custom.dialogs.DialogContract.DialogListListener
import ru.tsu.ibrahimfall.notforget.app.custom.dialogs.TextSelectionDialog
import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.app.fragments.main.MainValues
import ru.tsu.ibrahimfall.notforget.app.fragments.main.creation.CreationContract.Presenter
import ru.tsu.ibrahimfall.notforget.app.fragments.main.creation.CreationContract.View
import ru.tsu.ibrahimfall.notforget.interactor.categories.CategoriesInteractor
import ru.tsu.ibrahimfall.notforget.interactor.priorities.PrioritiesInteractor
import ru.tsu.ibrahimfall.notforget.interactor.tasks.TasksInteractor
import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment
import ru.tsu.ibrahimfall.notforget.repository.categories.CategoriesRepository
import ru.tsu.ibrahimfall.notforget.repository.priorities.PrioritiesRepository
import ru.tsu.ibrahimfall.notforget.repository.tasks.TasksRepository
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showSnackBar
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.showToast

class CreationFragment : NavigationFragment(), View {

    private lateinit var presenter: Presenter

    override var layoutId: Int = R.layout.fragment_creation
    override var name: String = "fragment-creation"

    private lateinit var categoriesDialog: BaseDialog
    private lateinit var prioritiesDialog: BaseDialog

    private val confirmationDialog: BaseDialog by lazy {
        with(activity!!) {
            ConfirmationDialog(this).apply {
                title = resources.getString(R.string.save_it)
                confirmText = resources.getString(R.string.yes)
                cancelText = resources.getString(R.string.cancel)
            }.setConfirmationListener {
                presenter.createTask()
            }
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CreationRepositoryManager(

            TasksRepository(TasksInteractor.getInstance()),
            CategoriesRepository(CategoriesInteractor.getInstance()),
            PrioritiesRepository(PrioritiesInteractor.getInstance())

        ).let { repository -> presenter = CreationPresenter(this, repository) }


        presenter.loadTaskSections()

        save.setOnClickListener {
            confirmationDialog.show()
        }

        back.setOnClickListener {
            navigationManager.requestBackPress()
        }
    }


    override fun onTaskCreated() {
        MainValues.shouldRefresh = true
        activity!!.showToast(activity!!.resources.getString(R.string.item_saved_succesfully))
        navigationManager.requestBackPress()
    }

    override fun showMessage(message: String) {
        fragmentView.showSnackBar(message)
    }

    override fun getTitle(): String = title.text.toString()

    override fun getDescription(): String = description.text.toString()

    override fun onCategoriesPrepared(dataCount: Int) {

        val onSelected = { text: String, position: Int ->
            presenter.onCategorySelected(position)
            category.setText(text)
            categoriesDialog.dismiss()
        }

        val onBind = { holder: HoldersContract.TextHolder ->
            presenter.bindCategory(holder)
        }

        categoriesDialog = createTextDialog(dataCount, onSelected, onBind)

        category.setOnClickListener {
            categoriesDialog.show()
        }

    }

    override fun onPrioritiesPrepared(dataCount: Int) {

        val onSelected = { text: String, position: Int ->
            presenter.onPrioritySelected(position)
            priority.setText(text)
            prioritiesDialog.dismiss()
        }

        val onBind = { holder: HoldersContract.TextHolder ->
            presenter.bindPriority(holder)
        }

        prioritiesDialog = createTextDialog(dataCount, onSelected, onBind)

        priority.setOnClickListener {
            prioritiesDialog.show()
        }

    }

    private fun createTextDialog(
        dataCount: Int,
        onSelected: (String, Int) -> Unit,
        onBind: (HoldersContract.TextHolder) -> Unit
    ): TextSelectionDialog = TextSelectionDialog(
        context,
        getSelectionAdapter(onSelected, onBind).also { it.setDataCount(dataCount) })


    private fun getSelectionAdapter(
        onSelected: (text: String, position: Int) -> Unit,
        onBind: (holder: HoldersContract.TextHolder) -> Unit
    ): TextSelectionDialog.Adapter =
        TextSelectionDialog.Adapter(object : DialogListListener<HoldersContract.TextHolder> {

            override fun onItemSelected(holder: HoldersContract.TextHolder, position: Int) {
                onSelected.invoke(holder.getText(), position)
            }

            override fun bindHolder(holder: HoldersContract.TextHolder) {
                onBind(holder)
            }

        })


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

}