package com.example.dimi.fridgepay.domain.main

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import com.example.dimi.fridgepay.model.ViewDynamicStateMain
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MainInteractor {

    fun refreshProducts(): Single<ProductsParsed>

    fun getUiState(): Observable<ViewDynamicStateMain>

    fun productAdded(productDisplayable: ProductDisplayable.Product): Completable

    fun productRemoved(productDisplayable: ProductDisplayable.Product): Completable

    fun listenProductsBought(observable: Observable<Any>): Observable<Any>

}