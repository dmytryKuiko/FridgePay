package com.example.dimi.fridgepay.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.dimi.fridgepay.data.db.TableNames

sealed class ProductDisplayable {
    @Entity(tableName = TableNames.TABLE_PRODUCT)
    data class Product(
        val imageName: String,
        val imageFallback: String,
        val name: String,
        val price: Double,
        val chosen: Int = 0,

        @PrimaryKey(autoGenerate = true)
        val id: Int? = null
    ) : ProductDisplayable()

    object Last : ProductDisplayable()
}