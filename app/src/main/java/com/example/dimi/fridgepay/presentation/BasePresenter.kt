package com.example.dimi.fridgepay.presentation

import android.arch.lifecycle.LiveData

interface BasePresenter<T> {
    fun getData(): LiveData<T>

    fun disposeDisposables()
}