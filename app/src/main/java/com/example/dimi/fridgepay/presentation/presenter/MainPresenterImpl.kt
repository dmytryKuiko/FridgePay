package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.domain.MainDomainMapper
import com.example.dimi.fridgepay.domain.MainInteractor
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ToolbarModel
import com.example.dimi.fridgepay.model.UiStateMain
import com.example.dimi.fridgepay.model.ViewDynamicStateMain
import com.example.dimi.fridgepay.utils.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(
    private val interactor: MainInteractor,
    private val mapper: MainDomainMapper,
    private val router: Router,
    private val schedulersProvider: SchedulersProvider,
    private val resourceManager: ResourceManager
) : MainPresenter {

    companion object {
        val TOOLBAR_MODEL: ToolbarModel = ToolbarModel(backVisibility = false, basketVisibility = true, countBasketVisibility = true)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val rxBindingCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val uiStateLiveData: MutableLiveData<UiStateMain> = MutableLiveData()

    private val notificationSingleLiveData: SingleEventLiveData<String> = SingleEventLiveData()

    private val productsLiveData: MutableLiveData<List<ProductDisplayable>> = MutableLiveData()

    init {
        refreshProducts()
        interactor.getUiState()
            .startWith(ViewDynamicStateMain(basketCount = 0, buyButtonEnabled = false))
            .subscribeOn(schedulersProvider.io())
            .subscribe(
                { uiStateLiveData.postValue(UiStateMain(TOOLBAR_MODEL, it)) },
                this::handleError
            )
            .addTo(compositeDisposable)
    }

    override fun getData(): LiveData<List<ProductDisplayable>> = productsLiveData

    override fun getNotification(): LiveData<String> = notificationSingleLiveData

    override fun disposeRxBinding() {
        rxBindingCompositeDisposable.clear()
        //Do not need to dispose compositeDisposable because we have only one activity
        // and presenter and other objects from domain and data layer for mainFragment have not to be destroyed
    }

    override fun getUiState(): LiveData<UiStateMain> = uiStateLiveData

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

    override fun listenBuyButton(observable: Observable<Any>) {
        interactor.listenProductsBought(observable)
            .map { resourceManager.getString(R.string.fragment_main_toast_text) }
            .subscribe((notificationSingleLiveData::postValue), (this::handleError))
            .addTo(rxBindingCompositeDisposable)
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