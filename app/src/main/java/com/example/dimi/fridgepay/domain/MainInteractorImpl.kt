package com.example.dimi.fridgepay.domain

import com.example.dimi.fridgepay.data.MainRepository
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MainInteractorImpl
@Inject constructor(private val repository: MainRepository) : MainInteractor {

    override fun refreshProducts(): Single<ProductsParsed> =
        repository.getProducts()
            .flatMap {
                val sortedList = it.listProducts.sortedWith(compareBy ({ it.name.toLowerCase() }, {it.contentVolume}) )
                Single.just(ProductsParsed(version = it.version,
                    updatedAt = it.updatedAt, imagePath = it.imagePath,
                    placeHolderImagePath = it.placeHolderImagePath, currency = it.currency,
                    contentVolumeUnit = it.contentVolumeUnit, listProducts = sortedList))
            }

    override fun getBasketCount(): Observable<Int> {
        return repository.getBasketCount().toObservable()
            .flatMap { Observable.just(it.size)}
    }

    override fun productAdded(productDisplayable: ProductDisplayable.Product): Completable =
        Completable.fromCallable { repository.storeProduct(productDisplayable) }

    override fun productRemoved(productDisplayable: ProductDisplayable.Product): Completable =
        Completable.fromCallable { repository.deleteProduct(productDisplayable) }
}