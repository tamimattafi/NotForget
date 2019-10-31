package ru.tsu.ibrahimfall.notforget.app.custom.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import ru.tsu.ibrahimfall.notforget.utils.AppUtils.convertDpToPixel

class DialogRecycler(context: Context, attributeSet: AttributeSet) :
    RecyclerView(context, attributeSet) {
    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(
            widthSpec,
            MeasureSpec.makeMeasureSpec(context.convertDpToPixel(300F).toInt(), MeasureSpec.AT_MOST)
        )
    }
}