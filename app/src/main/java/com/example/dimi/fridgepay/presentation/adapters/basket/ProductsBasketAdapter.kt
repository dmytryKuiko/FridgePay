package com.example.dimi.fridgepay.presentation.adapters.basket

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ProductsBasketAdapter: ListDelegationAdapter<MutableList<ProductDisplayable>>() {

    init {
        items = mutableListOf()
        delegatesManager.run {
            addDelegate(BasketDisplayableAdapter())
        }
    }

    fun setNewData(productsList: List<ProductDisplayable>) {
        items.apply { clear() }.addAll(productsList)
        notifyDataSetChanged()
    }
}