package com.example.dimi.fridgepay.di.modules

import com.example.dimi.fridgepay.data.*
import com.example.dimi.fridgepay.di.scopes.ActivityScope
import com.example.dimi.fridgepay.domain.BasketInteractor
import com.example.dimi.fridgepay.domain.BasketInteractorImpl
import com.example.dimi.fridgepay.domain.MainInteractor
import com.example.dimi.fridgepay.domain.MainInteractorImpl
import com.example.dimi.fridgepay.presentation.presenter.*
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @ActivityScope
    @Binds
    internal abstract fun bindActivityPresenter(presenter: ActivityPresenterImpl): ActivityPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindMainPresenter(presenter: MainPresenterImpl): MainPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindBasketPresenter(presenter: BasketPresenterImpl): BasketPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindMainInteractor(interactor: MainInteractorImpl): MainInteractor

    @ActivityScope
    @Binds
    internal abstract fun bindBasketInteractor(interactor: BasketInteractorImpl): BasketInteractor

    @ActivityScope
    @Binds
    internal abstract fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @ActivityScope
    @Binds
    internal abstract fun bindBasketRepository(repository: BasketRepositoryImpl): BasketRepository

    @ActivityScope
    @Binds
    internal abstract fun bindMainStore(store: MainStoreImpl): MainStore

    @ActivityScope
    @Binds
    internal abstract fun bindBasketStore(store: BasketStoreImpl): BasketStore
}