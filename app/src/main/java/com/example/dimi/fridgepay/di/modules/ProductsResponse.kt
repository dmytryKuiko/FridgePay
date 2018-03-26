package com.example.dimi.fridgepay.di.modules

import com.example.dimi.fridgepay.model.Product

class ProductsResponse(
    val version: Int?,
    val updatedAt: String?,
    val imagePath: String?,
    val placeHolderImagePath: String?,
    val currency: String?,
    val contentVolumeUnit: String?,
    val products: List<Product>?
)