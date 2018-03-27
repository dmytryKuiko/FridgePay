package com.example.dimi.fridgepay.di.modules

import com.example.dimi.fridgepay.data.basket.BasketRepository
import com.example.dimi.fridgepay.data.basket.BasketRepositoryImpl
import com.example.dimi.fridgepay.data.basket.BasketStore
import com.example.dimi.fridgepay.data.basket.BasketStoreImpl
import com.example.dimi.fridgepay.data.main.MainRepository
import com.example.dimi.fridgepay.data.main.MainRepositoryImpl
import com.example.dimi.fridgepay.data.main.MainStore
import com.example.dimi.fridgepay.data.main.MainStoreImpl
import com.example.dimi.fridgepay.di.scopes.ActivityScope
import com.example.dimi.fridgepay.domain.basket.BasketInteractor
import com.example.dimi.fridgepay.domain.basket.BasketInteractorImpl
import com.example.dimi.fridgepay.domain.main.MainInteractor
import com.example.dimi.fridgepay.domain.main.MainInteractorImpl
import com.example.dimi.fridgepay.presentation.presenter.*
import com.example.dimi.fridgepay.presentation.presenter.basket.BasketPresenter
import com.example.dimi.fridgepay.presentation.presenter.basket.BasketPresenterImpl
import com.example.dimi.fridgepay.presentation.presenter.main.MainPresenter
import com.example.dimi.fridgepay.presentation.presenter.main.MainPresenterImpl
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