package com.example.dimi.fridgepay.presentation.presenter

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.presentation.BasePresenter
import com.example.dimi.fridgepay.presentation.ToolbarPresenter

interface BasketPresenter : BasePresenter<List<ProductDisplayable>>, ToolbarPresenter {

    fun backClicked()
}