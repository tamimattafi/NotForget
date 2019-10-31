@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package ru.tsu.ibrahimfall.notforget.utils


import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import ru.tsu.ibrahimfall.notforget.R


object AppUtils {

    fun Context.convertDpToPixel(dp: Float): Float {
        return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getDrawable(context: Context, drawableId: Int): Drawable? {
        return ResourcesCompat.getDrawable(context.resources, drawableId, null)
    }

    @ColorInt
    fun getColor(context: Context, colorId: Int): Int {
        return ResourcesCompat.getColor(context.resources, colorId, null)
    }

    fun Context.showToast(text: String? = null) {
        Toast.makeText(
            this,
            text ?: resources.getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }

    fun View.showSnackBar(text: String? = null, @ColorInt color: Int = Color.DKGRAY) {
        Snackbar.make(
            this,
            text ?: context.resources.getString(R.string.something_went_wrong),
            Snackbar.LENGTH_SHORT
        )
            .setBackgroundTint(color)
            .show()
    }


    fun Context.isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    fun enableViews(enable: Boolean, vararg views: View) {
        for (view in views) {
            view.isEnabled = enable
        }
    }

    fun Activity.isActivityPortrait() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}