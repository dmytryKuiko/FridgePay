package com.example.dimi.fridgepay.data.network

import com.example.dimi.fridgepay.model.ProductsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceProductsApi {

    @GET("mobile_team/fridge_pay/v1/products.json")
    fun getAllProducts(): Single<ProductsResponse>
}