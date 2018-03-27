package com.example.dimi.fridgepay.data.basket

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable

interface BasketStore {

    fun getProducts(): Flowable<List<ProductDisplayable.Product>>
}