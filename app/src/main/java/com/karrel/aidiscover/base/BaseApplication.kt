package com.karrel.aidiscover.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initFresco()
    }


    private fun initFresco() {
        Fresco.initialize(this)
    }
}