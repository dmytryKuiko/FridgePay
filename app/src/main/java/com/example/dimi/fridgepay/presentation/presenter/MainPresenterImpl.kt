package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.dimi.fridgepay.domain.MainDomainMapper
import com.example.dimi.fridgepay.domain.MainInteractor
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ToolbarModel
import com.example.dimi.fridgepay.utils.SchedulersProvider
import com.example.dimi.fridgepay.utils.ScreenKeys
import com.example.dimi.fridgepay.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(
    private val interactor: MainInteractor,
    private val mapper: MainDomainMapper,
    private val router: Router,
    private val schedulersProvider: SchedulersProvider
) : MainPresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val toolbarLiveData: MutableLiveData<ToolbarModel> = MutableLiveData()

    private val basketCountLiveData: MutableLiveData<Int> = MutableLiveData()

    private val productsLiveData: MutableLiveData<List<ProductDisplayable>> = MutableLiveData()

    init {
        toolbarLiveData.value =
                ToolbarModel(backVisibility = View.INVISIBLE, basketVisibility = View.VISIBLE)
        refreshProducts()
        interactor.getBasketCount()
            .startWith(0)
            .subscribeOn(schedulersProvider.io())
            .subscribe(
                { basketCountLiveData.postValue(it) },
                this::handleError
            )
            .addTo(compositeDisposable)
    }

    override fun getData(): LiveData<List<ProductDisplayable>> = productsLiveData

    override fun disposeDisposables() {
        compositeDisposable.clear()
    }

    override fun getBasketCount(): LiveData<Int> = basketCountLiveData

    override fun getToolbarData(): LiveData<ToolbarModel> = toolbarLiveData

    override fun basketClicked() {
        router.navigateTo(ScreenKeys.BASKET_SCREEN)
    }

    override fun productClicked(product: ProductDisplayable.Product) {
        interactor.productAdded(product)
            .subscribeOn(schedulersProvider.computation())
            .subscribe({}, this::handleError)
            .addTo(compositeDisposable)
    }

    override fun productLongClicked(product: ProductDisplayable.Product) {
        interactor.productRemoved(product)
            .subscribeOn(schedulersProvider.computation())
            .subscribe({}, this::handleError)
            .addTo(compositeDisposable)
    }

    override fun swipeRefreshed() {
        refreshProducts()
    }

    override fun onBackPressed() {
        router.finishChain()
    }

    private fun refreshProducts() {
        interactor.refreshProducts()
            .map(mapper)
            .subscribe(
                { productsLiveData.postValue(it) },
                this::handleError
            ).addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        Timber.d(throwable)
    }

}