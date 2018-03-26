package com.example.dimi.fridgepay.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        injectModule()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    abstract fun injectModule()

}