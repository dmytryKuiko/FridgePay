package com.example.dimi.fridgepay.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val shownFlag = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, Observer { t: T? ->
            if (shownFlag.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun postValue(value: T?) {
        shownFlag.set(true)
        super.postValue(value)
    }

    fun call() = postValue(null)
}