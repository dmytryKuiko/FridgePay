package com.example.dimi.fridgepay.domain.main

import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ProductsParsed
import io.reactivex.functions.Function
import javax.inject.Inject

class MainDomainMapper
@Inject constructor() : Function<ProductsParsed, List<ProductDisplayable>> {
    override fun apply(products: ProductsParsed): List<ProductDisplayable> {
        val list = mutableListOf<ProductDisplayable>()
        products.listProducts.forEach {
            list.add(
                ProductDisplayable.Product(
                    imageName = "${products.imagePath}${it.imageName}",
                    imageFallback = products.placeHolderImagePath,
                    name = it.name,
                    price = it.price
                )
            )
        }

        return list.apply { add(ProductDisplayable.Last) }
    }
}