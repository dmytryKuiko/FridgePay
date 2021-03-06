package com.example.dimi.fridgepay.presentation.adapters.main

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ProductsMainAdapter(
    val clickProduct: (ProductDisplayable.Product) -> Unit,
    val longClickProduct: (ProductDisplayable.Product) -> Unit
): ListDelegationAdapter<MutableList<ProductDisplayable>>() {

    init {
        items = mutableListOf()
        delegatesManager.run {
            addDelegate(
                ProductDisplayableProductAdapter(
                    clickProduct,
                    longClickProduct
                )
            )
            addDelegate(ProductDisplayableLastAdapter())
        }
    }

    fun setNewData(productsList: List<ProductDisplayable>) {
        items.apply { clear() }.addAll(productsList)
        notifyDataSetChanged()
    }
}