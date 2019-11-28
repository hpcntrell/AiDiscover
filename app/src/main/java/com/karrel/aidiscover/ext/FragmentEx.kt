package com.karrel.aidiscover.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(
    @IdRes containerViewId: Int,
    @NonNull fragment: Fragment
) {
    this.supportFragmentManager.beginTransaction()
        .replace(containerViewId, fragment)
        .commit()
}

fun Fragment.replaceFragment(
    @IdRes containerViewId: Int,
    @NonNull fragment: Fragment
) {
    this.fragmentManager!!.beginTransaction()
        .replace(containerViewId, fragment)
        .commitAllowingStateLoss()
}


inline fun <reified T : Activity> Fragment.startActivity() {
    val intent = Intent(context, T::class.java)
    context?.startActivity(intent)
}

inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle) {
    val intent = Intent(context, T::class.java)
    intent.putExtras(bundle)
    context?.startActivity(intent)
}

inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int) {
    val intent = Intent(context, T::class.java)
    startActivityForResult(intent, requestCode)
}

