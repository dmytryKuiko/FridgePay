package com.example.dimi.fridgepay.utils

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.visible(visible: Boolean) {
    this.visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}