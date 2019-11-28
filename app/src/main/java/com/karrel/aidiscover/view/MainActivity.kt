package com.karrel.aidiscover.view

import android.os.Bundle
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
    }
}
