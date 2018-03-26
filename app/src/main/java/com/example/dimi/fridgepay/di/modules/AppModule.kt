package com.example.dimi.fridgepay.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.dimi.fridgepay.App
import com.example.dimi.fridgepay.data.db.AppDatabase
import com.example.dimi.fridgepay.data.db.ProductDao
import com.example.dimi.fridgepay.utils.AppSchedulers
import com.example.dimi.fridgepay.utils.SchedulersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindSchedulersProvider(appSchedulers: AppSchedulers): SchedulersProvider

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun provideContext(app: App): Context = app.applicationContext

        @JvmStatic
        @Singleton
        @Provides
        fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "fridge_pay_db")
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.productDao()
    }
}