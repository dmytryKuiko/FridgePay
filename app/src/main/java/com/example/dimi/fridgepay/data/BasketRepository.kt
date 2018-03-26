package com.example.dimi.fridgepay.data

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable

interface BasketRepository  {

    fun getProducts(): Flowable<List<ProductDisplayable.Product>>
}