package com.example.dimi.fridgepay.di.modules

import com.example.dimi.fridgepay.data.network.ServiceProductsApi
import com.example.dimi.fridgepay.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder.build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRxJava2CallAdapterFactory(schedulers: SchedulersProvider): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(schedulers.io())

    @Provides
    fun provideRetrofit(
        retrofitBuilder: Retrofit.Builder,
        url: String,
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory,
        rxJava2CallAdapter: RxJava2CallAdapterFactory
    ): Retrofit =
        retrofitBuilder.baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(rxJava2CallAdapter)
            .build()

    @Singleton
    @Provides
    fun provideServiceProductsApi(retrofit: Retrofit) = retrofit.create(ServiceProductsApi::class.java)
}