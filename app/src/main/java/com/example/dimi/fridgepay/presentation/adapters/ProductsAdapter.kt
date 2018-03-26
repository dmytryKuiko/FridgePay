package com.example.dimi.fridgepay.presentation.adapters

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ProductsAdapter(
    val clickProduct: (ProductDisplayable.Product) -> Unit,
    val longClickProduct: (ProductDisplayable.Product) -> Unit
): ListDelegationAdapter<MutableList<ProductDisplayable>>() {

    init {
        items = mutableListOf()
        delegatesManager.run {
            addDelegate(ProductDisplayableProductAdapter(clickProduct, longClickProduct))
            addDelegate(ProductDisplayableLastAdapter())
        }
    }

    fun setNewData(productsList: List<ProductDisplayable>) {
        items.apply { clear() }.addAll(productsList)
        notifyDataSetChanged()
    }
}