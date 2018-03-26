package com.example.dimi.fridgepay.data

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(
    private val store: MainStore,
    private val serviceApi: ServiceProductsApi,
    private val mapper: MainDataMapper
) : MainRepository {

    override fun getProducts(): Single<ProductsParsed> = serviceApi.getAllProducts().map(mapper)

    override fun getBasketCount(): Flowable<List<ProductDisplayable.Product>> =
        store.getAllProducts()

    override fun storeProduct(product: ProductDisplayable.Product) {
        store.storeProduct(product)
    }

    override fun deleteProduct(product: ProductDisplayable.Product) {
        store.deleteProduct(product)
    }
}
