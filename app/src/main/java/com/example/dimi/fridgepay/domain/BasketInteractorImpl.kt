package com.example.dimi.fridgepay.domain

import com.example.dimi.fridgepay.data.BasketRepository
import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Observable
import javax.inject.Inject

class BasketInteractorImpl
@Inject constructor(private val repository: BasketRepository) : BasketInteractor {

    override fun getProducts(): Observable<List<ProductDisplayable.Product>> =
        repository.getProducts().toObservable()
}