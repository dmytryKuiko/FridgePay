package com.example.dimi.fridgepay.presentation.presenter.basket

import android.arch.lifecycle.LiveData
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.presentation.BasePresenter
import com.example.dimi.fridgepay.presentation.ToolbarPresenter

interface BasketPresenter : BasePresenter<List<ProductDisplayable>>, ToolbarPresenter {

    fun backClicked()

    fun getBasketPrice(): LiveData<String>
}