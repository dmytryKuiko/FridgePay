package com.example.dimi.fridgepay.data

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable
import javax.inject.Inject

class BasketRepositoryImpl
@Inject constructor(private val store: BasketStore) : BasketRepository {

    override fun getProducts(): Flowable<List<ProductDisplayable.Product>> =
        store.getProducts()
}