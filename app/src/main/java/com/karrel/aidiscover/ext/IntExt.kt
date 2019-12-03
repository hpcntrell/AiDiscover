package com.karrel.aidiscover.ext

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import androidx.annotation.StringRes

fun Int.dp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val statusBarHeight: Int
    get() {
        val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) Resources.getSystem().getDimensionPixelSize(resourceId) else 0
    }

val navigationBarHeight: Int
    get() {
        val resourceId = Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) Resources.getSystem().getDimensionPixelSize(resourceId) else 0
    }


fun Int.resString(context: Context): String {
    return context.getString(this)

}