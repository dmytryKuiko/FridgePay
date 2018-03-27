package com.example.dimi.fridgepay.presentation.presenter.basket

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.domain.basket.BasketInteractor
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ToolbarModel
import com.example.dimi.fridgepay.utils.ResourceManager
import com.example.dimi.fridgepay.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class BasketPresenterImpl
@Inject constructor(
    private val interactor: BasketInteractor,
    private val router: Router,
    private val resourceManager: ResourceManager
) : BasketPresenter {

    private val toolbarLiveData: MutableLiveData<ToolbarModel> = MutableLiveData()

    private val productsLiveData: MutableLiveData<List<ProductDisplayable>> = MutableLiveData()

    private val priceLiveData: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        toolbarLiveData.value =
                ToolbarModel(
                    backVisibility = true,
                    basketVisibility = false,
                    countBasketVisibility = false
                )
        interactor.getProducts()
            .subscribe((productsLiveData::postValue), (this::handleError))
            .addTo(compositeDisposable)

        interactor.getProductsPrice()
            .subscribe({
                val priceText = resourceManager.getString(R.string.fragment_basket_price_text)
                val currency = resourceManager.getString(R.string.fragment_basket_currency_symbol)
                priceLiveData.postValue("$priceText $it$currency")
                priceLiveData::postValue
            }, (this::handleError))
            .addTo(compositeDisposable)

    }

    override fun getData(): LiveData<List<ProductDisplayable>> = productsLiveData

    override fun getToolbarData(): LiveData<ToolbarModel> = toolbarLiveData

    override fun getBasketPrice(): LiveData<String> = priceLiveData

    override fun backClicked() {
        router.exit()
    }

    private fun handleError(throwable: Throwable) {
        Timber.d(throwable)
    }
}