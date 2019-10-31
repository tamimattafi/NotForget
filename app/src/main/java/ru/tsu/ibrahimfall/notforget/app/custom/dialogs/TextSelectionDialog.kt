package ru.tsu.ibrahimfall.notforget.app.custom.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_string_selection.view.*
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.HoldersContract
import ru.tsu.ibrahimfall.notforget.app.fragments.global.holders.TextViewHolder

class TextSelectionDialog(context: Context?, adapter: Adapter) : DialogContract.BaseDialog {

    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.dialog_string_selection, null, false)
    private val dialog: AlertDialog = AlertDialog.Builder(context).setView(dialogView).create()

    init {

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(dialogView.recycler) {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            this.adapter = adapter
        }

    }

    class Adapter(private val listener: DialogContract.DialogListListener<HoldersContract.TextHolder>) :
        RecyclerView.Adapter<TextViewHolder>(), DialogContract.Adapter {

        private var dataCount: Int = 0

        override fun setDataCount(dataCount: Int) {
            this.dataCount = dataCount
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder =
            TextViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_view_text,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = dataCount

        override fun onBindViewHolder(holder: TextViewHolder, position: Int) {

            holder.apply {
                listPosition = position

                setHolderClickListener {
                    listener.onItemSelected(holder, position)
                }

            }

            listener.bindHolder(holder)
        }

    }

    override fun show() {
        dialog.show()
    }

    override fun dismiss() {
        dialog.dismiss()
    }

}