package com.example.dimi.fridgepay.data

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable

interface MainStore {

    fun getAllProducts(): Flowable<List<ProductDisplayable.Product>>

    fun storeProduct(product: ProductDisplayable.Product)

    fun deleteProduct(product: ProductDisplayable.Product)
}