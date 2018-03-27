package com.example.dimi.fridgepay.data.basket

import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable
import javax.inject.Inject

class BasketRepositoryImpl
@Inject constructor(private val store: BasketStore) :
    BasketRepository {

    override fun getProducts(): Flowable<List<ProductDisplayable.Product>> =
        store.getProducts()
}