package com.example.dimi.fridgepay.domain

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface MainInteractor {

    fun refreshProducts(): Single<ProductsParsed>

    fun getBasketCount(): Observable<Int>

    fun productAdded(productDisplayable: ProductDisplayable.Product): Completable

    fun productRemoved(productDisplayable: ProductDisplayable.Product): Completable

}