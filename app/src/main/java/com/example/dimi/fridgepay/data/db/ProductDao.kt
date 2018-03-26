package com.example.dimi.fridgepay.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dimi.fridgepay.model.ProductDisplayable
import io.reactivex.Flowable

@Dao
abstract class ProductDao : BaseDao<ProductDisplayable.Product> {

    @Query("SELECT * FROM ${TableNames.TABLE_PRODUCT}")
    abstract fun getAllProducts(): Flowable<List<ProductDisplayable.Product>>

    @Query("DELETE FROM ${TableNames.TABLE_PRODUCT}")
    abstract fun deleteAllProducts()

    @Query("DELETE FROM ${TableNames.TABLE_PRODUCT} WHERE name = :name AND price = :price")
    abstract fun deleteProduct(name: String, price: Double)

//    @Query("DELETE FROM ${TableNames.TABLE_PRODUCT} WHERE name = :name AND price = :price LIMIT 1")
//    abstract fun deleteProduct(name: String, price: Double)
}