package com.example.dimi.fridgepay.data.main

import com.example.dimi.fridgepay.model.ProductsResponse
import com.example.dimi.fridgepay.model.ProductsParsed
import io.reactivex.functions.Function
import javax.inject.Inject

class MainDataMapper
@Inject constructor() : Function<ProductsResponse, ProductsParsed> {

    override fun apply(response: ProductsResponse): ProductsParsed {
        val version = response.version ?: 1
        val updateAt = response.updatedAt ?: "unavailable"
        val imagePath = response.imagePath ?: "unavailable"
        val placeHolderImagePath = response.placeHolderImagePath ?: "unavailable"
        val currency = response.currency ?: "unavailable"
        val contentVolumeUnit = response.contentVolumeUnit ?: "unavailable"
        val listProducts = response.products ?: emptyList()
        return ProductsParsed(
            version = version, updatedAt = updateAt, imagePath = imagePath,
            placeHolderImagePath = placeHolderImagePath, currency = currency,
            contentVolumeUnit = contentVolumeUnit, listProducts = listProducts
        )
    }
}