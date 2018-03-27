package com.example.dimi.fridgepay.domain.basket

import com.example.dimi.fridgepay.data.basket.BasketRepository
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import java.math.RoundingMode
import javax.inject.Inject

class BasketInteractorImpl
@Inject constructor(private val repository: BasketRepository) :
    BasketInteractor {

    private val priceRelay: BehaviorRelay<Double> = BehaviorRelay.create()

    override fun getProducts(): Observable<List<ProductDisplayable.Product>> =
        repository.getProducts()
            .toObservable()
            .doOnNext {
                priceRelay.accept(
                    it.sumByDouble { it.price }.toBigDecimal().setScale(
                        2,
                        RoundingMode.HALF_UP
                    ).toDouble()
                )
            }

    override fun getProductsPrice(): Observable<Double> = priceRelay
}