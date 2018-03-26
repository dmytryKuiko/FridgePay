package com.example.dimi.fridgepay.di.components

interface BaseComponent<in T> {
    fun inject(activity: T)
}