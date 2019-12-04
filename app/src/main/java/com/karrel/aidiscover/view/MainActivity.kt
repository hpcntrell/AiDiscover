package com.karrel.aidiscover.view

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.replaceFragment

class MainActivity : AppCompatActivity() {

    private val selectProfileFragment: Fragment by lazy { AiDiscoverFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.container, selectProfileFragment)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#f1f3f5")

    }
}
