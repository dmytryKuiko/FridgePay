package com.example.dimi.fridgepay.data

import com.example.dimi.fridgepay.data.db.ProductDao
import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable
import javax.inject.Inject

class MainStoreImpl
@Inject constructor(private val productDao: ProductDao) : MainStore {

    override fun getAllProducts(): Flowable<List<ProductDisplayable.Product>> =
        productDao.getAllProducts()

    override fun storeProduct(product: ProductDisplayable.Product) {
        productDao.insert(product)
    }

    override fun deleteProduct(product: ProductDisplayable.Product) {
        productDao.deleteProduct(product.name, product.price)
    }
}