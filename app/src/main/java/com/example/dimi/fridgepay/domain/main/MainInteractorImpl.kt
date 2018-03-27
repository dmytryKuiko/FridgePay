package com.example.dimi.fridgepay.domain.main

import com.example.dimi.fridgepay.data.main.MainRepository
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import com.example.dimi.fridgepay.model.ViewDynamicStateMain
import com.example.dimi.fridgepay.utils.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MainInteractorImpl
@Inject constructor(private val repository: MainRepository,
                    private val schedulers: SchedulersProvider) :
    MainInteractor {

    override fun refreshProducts(): Single<ProductsParsed> =
        repository.getProducts()
            .flatMap {
                val sortedList = it.listProducts.sortedWith(
                    compareBy({ it.name.toLowerCase() }, { it.contentVolume })
                )
                Single.just(
                    ProductsParsed(
                        version = it.version,
                        updatedAt = it.updatedAt, imagePath = it.imagePath,
                        placeHolderImagePath = it.placeHolderImagePath, currency = it.currency,
                        contentVolumeUnit = it.contentVolumeUnit, listProducts = sortedList
                    )
                )
            }

    override fun getUiState(): Observable<ViewDynamicStateMain> =
        repository.getBasketCount().toObservable()
            .flatMap {
                Observable.just(ViewDynamicStateMain(it.size, it.isNotEmpty()))
            }

    override fun productAdded(productDisplayable: ProductDisplayable.Product): Completable =
        Completable.fromCallable { repository.storeProduct(productDisplayable) }

    override fun productRemoved(productDisplayable: ProductDisplayable.Product): Completable =
        Completable.fromCallable { repository.deleteProduct(productDisplayable) }

    override fun listenProductsBought(observable: Observable<Any>): Observable<Any> =
        observable.flatMap {
            /**
             * Perform request for buying products
             * in case of success -> delete items from DB
             * F.e. MainRepository has a next method  requestBuyProducts():Completable which takes all data from DB
             * in case of success we are clearing the DB and sending successfull result back to presenter for showing a Toast
             * for a user
             */
            Completable.fromCallable { repository.buyProducts() }
                .subscribeOn(schedulers.io())
                .andThen(Observable.just(true))
        }
}