package com.example.dimi.fridgepay.domain.basket

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Observable

interface BasketInteractor {

    fun getProducts(): Observable<List<ProductDisplayable.Product>>

    fun getProductsPrice(): Observable<Double>
}