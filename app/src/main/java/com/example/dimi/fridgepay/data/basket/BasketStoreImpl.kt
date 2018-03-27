package com.example.dimi.fridgepay.data.basket

import com.example.dimi.fridgepay.data.db.ProductDao
import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable
import javax.inject.Inject

class BasketStoreImpl
@Inject constructor(private val productDao: ProductDao) :
    BasketStore {

    override fun getProducts(): Flowable<List<ProductDisplayable.Product>> =
        productDao.getAllProducts()

}