package com.example.dimi.fridgepay.model

class ProductsParsed(val version: Int,
                     val updatedAt: String,
                     val imagePath: String,
                     val placeHolderImagePath: String,
                     val currency: String,
                     val contentVolumeUnit: String,
                     val listProducts: List<Product>) {
}