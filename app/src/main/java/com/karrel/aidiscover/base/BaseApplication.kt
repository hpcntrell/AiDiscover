package com.karrel.aidiscover.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.karrel.aidiscover.BuildConfig
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initFresco()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    private fun initFresco() {
        Fresco.initialize(this)
    }
}