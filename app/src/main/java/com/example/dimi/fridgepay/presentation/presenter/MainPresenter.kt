package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.UiStateMain
import com.example.dimi.fridgepay.presentation.BasePresenter
import io.reactivex.Observable

interface MainPresenter : BasePresenter<List<ProductDisplayable>> {

    fun dispodeRxBinding()

    fun getUiState(): LiveData<UiStateMain>

    fun getNotification(): LiveData<String>

    fun basketClicked()

    fun productClicked(product: ProductDisplayable.Product)

    fun productLongClicked(product: ProductDisplayable.Product)

    fun listenBuyButton(observable: Observable<Any>)

    fun swipeRefreshed()

    fun onBackPressed()
}