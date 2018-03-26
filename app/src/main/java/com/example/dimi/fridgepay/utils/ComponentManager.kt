package com.example.dimi.fridgepay.utils

import com.example.dimi.fridgepay.App
import com.example.dimi.fridgepay.di.components.AppComponent
import com.example.dimi.fridgepay.di.components.DaggerAppComponent
import com.example.dimi.fridgepay.di.components.MainComponent

object ComponentManager {

    private const val API_URL = "https://projekte.raysono.com/"

    private lateinit var appComponent: AppComponent

    private lateinit var mainComponent: MainComponent

    @Synchronized
    fun initComponents(app: App) {
        if (!::appComponent.isInitialized) {
            appComponent = DaggerAppComponent.builder().application(app)
                .url(API_URL)
                .build()
            mainComponent = appComponent.mainComponentBuilder().build()
        }
    }

    fun getMainComponent(): MainComponent = mainComponent
}