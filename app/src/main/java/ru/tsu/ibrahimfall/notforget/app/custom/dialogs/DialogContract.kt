package ru.tsu.ibrahimfall.notforget.app.custom.dialogs

import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract

interface DialogContract {

    interface BaseDialog {
        fun show()
        fun dismiss()
    }

    interface DialogListListener<Holder : MvpBaseContract.Holder> {
        fun onItemSelected(holder: Holder, position: Int)
        fun bindHolder(holder: Holder)
    }

    interface Adapter {
        fun setDataCount(dataCount: Int)
    }

    interface ConfirmationDialog : BaseDialog {
        fun setConfirmationListener(onConfirm: () -> Unit): ConfirmationDialog
        fun setCancelListener(onCancel: () -> Unit): ConfirmationDialog
    }

}