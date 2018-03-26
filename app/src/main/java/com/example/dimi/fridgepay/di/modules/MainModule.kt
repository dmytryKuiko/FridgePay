package com.example.dimi.fridgepay.di.modules

import com.example.dimi.fridgepay.data.MainRepository
import com.example.dimi.fridgepay.data.MainRepositoryImpl
import com.example.dimi.fridgepay.data.MainStore
import com.example.dimi.fridgepay.data.MainStoreImpl
import com.example.dimi.fridgepay.di.scopes.ActivityScope
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
    internal abstract fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @ActivityScope
    @Binds
    internal abstract fun bindMainStore(store: MainStoreImpl): MainStore
}