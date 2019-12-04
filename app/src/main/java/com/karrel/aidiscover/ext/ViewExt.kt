package com.karrel.aidiscover.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun setAlpha(alpha: Float, vararg items: View) {
    items.forEach { it.alpha = alpha }
}

fun setVisible(isVisible: Boolean, vararg items: View) {
    items.forEach { it.isVisible = isVisible }
}

fun setVisibility(visibility: Int, vararg items: View) {
    items.forEach { it.visibility = visibility }
}

fun View.setScale(scale: Float) {
    scaleX = scale
    scaleY = scale
}

fun EditText.hideKeyboard() {
    val imm: InputMethodManager? = context.getSystemService()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.getLocationInWindow(): Pair<Int, Int> {
    val location = IntArray(2)
    getLocationInWindow(location)
    return location[0] to location[1]
}

fun View.getLocationOnScreen(): Pair<Int, Int> {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return location[0] to location[1]
}

inline fun <reified T : Activity> View.startActivity(bundle: Bundle) {
    val intent = Intent(context, T::class.java)
    intent.putExtras(bundle)
    context?.startActivity(intent)
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun RecyclerView.findFirstVisibleItemView(): View? {
    val layoutManager = layoutManager as? LinearLayoutManager ?: return null
    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
    return layoutManager.findViewByPosition(firstVisibleItem)
}

fun RecyclerView.findFirstVisibleItem(): Int {
    val layoutManager = layoutManager as? LinearLayoutManager ?: return Int.MIN_VALUE
    return layoutManager.findFirstVisibleItemPosition()
}

fun View.debouncedClicks(): Observable<Unit> {
    return clicks()
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .observeOnMainThread()
}

fun View.setOnDebouncedClickListener(listener: View.OnClickListener?) {
    clicks()
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .map { this }
        .observeOnMainThread()
        .subscribe {
            listener?.onClick(it)
        }
}


fun View.setOnDebouncedClickListener(action: (View) -> Unit) {
    clicks()
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .map { this }
        .observeOnMainThread()
        .subscribe(action::invoke)
}