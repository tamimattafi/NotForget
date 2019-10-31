package ru.tsu.ibrahimfall.notforget.app.custom.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog_confirmation.view.*
import ru.tsu.ibrahimfall.notforget.R

class ConfirmationDialog(activity: Activity) : DialogContract.ConfirmationDialog {

    private val view = LayoutInflater.from(activity).inflate(R.layout.dialog_confirmation, null)
    private val dialog =
        AlertDialog.Builder(activity).setCancelable(true).setView(view).create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    private var onConfirm: (() -> Unit)? = null
    private var onCancel: (() -> Unit)? = null

    var confirmText: String = activity.resources.getString(R.string.yes)
        set(value) {
            field = value
            view.confirm.text = value
        }

    var cancelText: String = activity.resources.getString(R.string.cancel)
        set(value) {
            field = value
            view.cancel.text = value
        }

    var title: String = activity.resources.getString(R.string.confirmation_dialog)
        set(value) {
            field = value
            view.title.text = value
        }

    init {
        with(view) {
            cancel.setOnClickListener {
                dialog.cancel()
            }

            confirm.setOnClickListener {
                onConfirm?.invoke()
                dismiss()
            }
        }
    }

    override fun setConfirmationListener(onConfirm: () -> Unit): DialogContract.ConfirmationDialog =
        this.also { it.onConfirm = onConfirm }

    override fun setCancelListener(onCancel: () -> Unit): DialogContract.ConfirmationDialog =
        this.also { it.onCancel = onCancel }

    override fun show() {
        dialog.show()
    }

    override fun dismiss() {
        dialog.dismiss()
    }


}