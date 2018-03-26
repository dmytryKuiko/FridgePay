package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.presentation.BasePresenter
import com.example.dimi.fridgepay.presentation.ToolbarPresenter

interface MainPresenter : BasePresenter<List<ProductDisplayable>>, ToolbarPresenter {

    fun getBasketCount(): LiveData<Int>

    fun basketClicked()

    fun productClicked(product: ProductDisplayable.Product)

    fun productLongClicked(product: ProductDisplayable.Product)

    fun swipeRefreshed()

    fun onBackPressed()
}