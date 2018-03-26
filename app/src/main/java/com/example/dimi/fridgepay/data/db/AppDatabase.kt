package com.example.dimi.fridgepay.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dimi.fridgepay.model.Product
import com.example.dimi.fridgepay.model.ProductDisplayable

@Database(entities = [ProductDisplayable.Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}