package com.example.dimi.fridgepay.data.main

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MainRepository {
    fun getProducts(): Single<ProductsParsed>

    fun getBasketCount(): Flowable<List<ProductDisplayable.Product>>

    fun storeProduct(product: ProductDisplayable.Product)

    fun deleteProduct(product: ProductDisplayable.Product)

    fun buyProducts()
}