package com.example.dimi.fridgepay

import android.app.Application
import com.example.dimi.fridgepay.utils.ComponentManager
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        ComponentManager.initComponents(this)
    }
}